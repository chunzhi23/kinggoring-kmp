package com.phoniler.kinggoring.view

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.nfc.NfcManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.phoniler.kinggoring.component.deviceSettingsItem
import com.phoniler.kinggoring.icon.IconDevices
import com.phoniler.kinggoring.icon.IconNfc

@Composable
actual fun getScreenWidth(): Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.dp
}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
actual fun DeviceView() {
    val bluetoothPermissionState =
        rememberPermissionState(Manifest.permission.BLUETOOTH_CONNECT)
    if (bluetoothPermissionState.status.isGranted) {
        DeviceViewWithGrantedPermission()
    } else {
        LaunchedEffect(Unit) {
            bluetoothPermissionState.launchPermissionRequest()
        }
    }
}

@Composable
fun DeviceViewWithGrantedPermission() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val nfcManager = context.getSystemService(Context.NFC_SERVICE) as NfcManager
    val nfcAdapter = nfcManager.defaultAdapter
    var isNfcEnabled by remember { mutableStateOf(false) }
    val intentNfc = Intent(Settings.ACTION_NFC_SETTINGS)

    val btManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val btAdapter = btManager.adapter
    var isBtEnabled by remember { mutableStateOf(false) }
    val intentBt = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)

    fun updateStatus() {
        isNfcEnabled = nfcAdapter?.isEnabled == true
        isBtEnabled = btAdapter.isEnabled == true
    }

    DisposableEffect(lifecycleOwner) {
        val observer =
            LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    updateStatus()
                }
            }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        updateStatus()
    }

    Column {
        deviceSettingsItem("NFC", IconNfc, isNfcEnabled) {
            context.startActivity(intentNfc)
        }
        deviceSettingsItem("키링 연결", IconDevices, isBtEnabled) {
            context.startActivity(intentBt)
        }
    }
}

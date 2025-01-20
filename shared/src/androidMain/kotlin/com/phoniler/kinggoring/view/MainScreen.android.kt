package com.phoniler.kinggoring.view

import android.bluetooth.BluetoothManager
import android.content.Context
import android.nfc.NfcManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
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
        rememberPermissionState(android.Manifest.permission.BLUETOOTH_CONNECT)
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

    val btManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val btAdapter = btManager.adapter
    var isBtEnabled by remember { mutableStateOf(false) }

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
        Text(if (isNfcEnabled) "NFC 활성화됨" else "NFC 비활성화됨")
    }
}

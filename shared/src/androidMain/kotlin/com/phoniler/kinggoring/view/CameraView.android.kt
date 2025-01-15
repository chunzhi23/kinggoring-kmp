package com.phoniler.kinggoring.view

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.phoniler.kinggoring.AndroidStorableImage
import com.phoniler.kinggoring.PlatformStorableImage
import com.phoniler.kinggoring.component.CircularButton
import com.phoniler.kinggoring.icon.IconOutlinedCamera
import com.phoniler.kinggoring.model.GpsPosition
import com.phoniler.kinggoring.model.PictureData
import com.phoniler.kinggoring.model.createCameraPictureData
import com.phoniler.kinggoring.toImageBitmap
import kinggoring.shared.generated.resources.Res
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import java.nio.ByteBuffer
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.absoluteValue

private val executor = Executors.newSingleThreadExecutor()

@OptIn(ExperimentalPermissionsApi::class)
@Composable
actual fun CameraView(
    modifier: Modifier,
    onCapture: (picture: PictureData.Camera, image: PlatformStorableImage) -> Unit,
) {
    val cameraPermissionState =
        rememberMultiplePermissionsState(
            listOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            ),
        )
    if (cameraPermissionState.allPermissionsGranted) {
        CameraWithGrantedPermission(modifier, onCapture)
    } else {
        LaunchedEffect(Unit) {
            cameraPermissionState.launchMultiplePermissionRequest()
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun CameraWithGrantedPermission(
    modifier: Modifier,
    onCapture: (picture: PictureData.Camera, image: PlatformStorableImage) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val viewScope = rememberCoroutineScope()
    var cameraProvider: ProcessCameraProvider? by remember { mutableStateOf(null) }

    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    var isFrontCamera by rememberSaveable { mutableStateOf(false) }
    val cameraSelector =
        remember(isFrontCamera) {
            val lensFacing =
                if (isFrontCamera) {
                    CameraSelector.LENS_FACING_FRONT
                } else {
                    CameraSelector.LENS_FACING_BACK
                }
            CameraSelector
                .Builder()
                .requireLensFacing(lensFacing)
                .build()
        }

    DisposableEffect(Unit) {
        onDispose {
            cameraProvider?.unbindAll()
        }
    }

    LaunchedEffect(isFrontCamera) {
        cameraProvider =
            suspendCoroutine<ProcessCameraProvider> { continuation ->
                ProcessCameraProvider.getInstance(context).also { cameraProvider ->
                    cameraProvider.addListener({
                        continuation.resume(cameraProvider.get())
                    }, executor)
                }
            }
        cameraProvider?.unbindAll()
        cameraProvider?.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture,
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }
    var capturePhotoStarted by remember { mutableStateOf(false) }

    Box(
        modifier =
            modifier.pointerInput(isFrontCamera) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount.absoluteValue > 50.0) {
                        isFrontCamera = !isFrontCamera
                    }
                }
            },
    ) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
        CircularButton(
            imageVector = IconOutlinedCamera,
            modifier = Modifier.align(Alignment.BottomCenter).padding(36.dp),
            enabled = !capturePhotoStarted,
        ) {
            fun addLocationInfoAndReturnResult(imageBitmap: ImageBitmap) {
                fun sendToStorage(gpsPosition: GpsPosition) {
                    onCapture(
                        createCameraPictureData(
                            name = "TODO: 이걸로 아침 점심 저녁 구분해야할 듯",
                            description = "TODO: 아님 이걸로 하거나",
                            gps = gpsPosition,
                        ),
                        AndroidStorableImage(imageBitmap),
                    )
                    capturePhotoStarted = false
                }
                LocationServices
                    .getFusedLocationProviderClient(context)
                    .getCurrentLocation(CurrentLocationRequest.Builder().build(), null)
                    .apply {
                        addOnSuccessListener {
                            sendToStorage(GpsPosition(it.latitude, it.longitude))
                        }
                        addOnFailureListener {
                            sendToStorage(GpsPosition(0.0, 0.0))
                        }
                    }
            }

            capturePhotoStarted = true
            imageCapture.takePicture(
                executor,
                object : OnImageCapturedCallback() {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        val byteArray: ByteArray = image.planes[0].buffer.toByteArray()
                        val imageBitmap = byteArray.toImageBitmap()
                        image.close()
                        addLocationInfoAndReturnResult(imageBitmap)
                    }
                },
            )
            viewScope.launch {
                // TODO: There is a known issue with Android emulator
                //  https://partnerissuetracker.corp.google.com/issues/161034252
                //  After 5 seconds delay, let's assume that the bug appears and publish a prepared photo
                delay(5000)
                if (capturePhotoStarted) {
                    addLocationInfoAndReturnResult(
                        Res.readBytes("files/android-emulator-photo.jpg").toImageBitmap(),
                    )
                }
            }
        }
        if (capturePhotoStarted) {
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp).align(Alignment.Center),
                color = Color.White.copy(alpha = 0.7f),
                strokeWidth = 8.dp,
            )
        }
    }
}

private fun ByteBuffer.toByteArray(): ByteArray {
    rewind() // Rewind the buffer to zero
    val data = ByteArray(remaining())
    get(data) // Copy the buffer into a byte array
    return data // Return the byte array
}

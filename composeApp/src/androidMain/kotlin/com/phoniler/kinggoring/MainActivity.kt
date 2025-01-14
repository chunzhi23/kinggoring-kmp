package com.phoniler.kinggoring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.phoniler.kinggoring.view.KinggoRingAndroid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KinggoRingAndroid()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    KinggoRingAndroid()
}

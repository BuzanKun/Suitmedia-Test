package com.collection.suitmediatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.collection.suitmediatest.ui.screen.first.FirstScreen
import com.collection.suitmediatest.ui.theme.SuitmediaTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuitmediaTestTheme {
                SuitmediaTestApp()
            }
        }
    }
}

@Composable
fun SuitmediaTestApp(
) {
    FirstScreen()
}

@Preview
@Composable
private fun SuitmediaTestAppPreview() {
    SuitmediaTestTheme {
        SuitmediaTestApp()
    }
}
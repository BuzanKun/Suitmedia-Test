package com.collection.suitmediatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.collection.suitmediatest.navigation.Screens
import com.collection.suitmediatest.ui.screen.first.FirstScreen
import com.collection.suitmediatest.ui.screen.second.SecondScreen
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
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.FirstScreen.route
    ) {
        composable(route = Screens.FirstScreen.route) {
            FirstScreen(navController = navController)
        }

        composable(route = Screens.SecondScreen.route) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Guest"
            SecondScreen(
                navController = navController,
                name = name
            )
        }
    }
}

@Preview
@Composable
private fun SuitmediaTestAppPreview() {
    SuitmediaTestTheme {
        SuitmediaTestApp()
    }
}
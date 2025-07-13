package com.collection.suitmediatest.navigation

sealed class Screens(val route: String) {
    data object FirstScreen : Screens("first")
    data object SecondScreen : Screens("second/{name}")
    data object ThirdScreen : Screens("third")
}
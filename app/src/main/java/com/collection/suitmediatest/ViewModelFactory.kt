package com.collection.suitmediatest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.collection.suitmediatest.data.remote.UserInjection
import com.collection.suitmediatest.data.remote.UserRepository
import com.collection.suitmediatest.ui.screen.third.ThirdScreenViewModel

class ViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            return ThirdScreenViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(UserInjection.provideRepository())
            }.also { INSTANCE = it }
    }
}
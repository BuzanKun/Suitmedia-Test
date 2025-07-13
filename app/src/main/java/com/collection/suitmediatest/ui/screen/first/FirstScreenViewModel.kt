package com.collection.suitmediatest.ui.screen.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FirstScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FirstScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: FirstScreenEvent) {
        when (event) {
            is FirstScreenEvent.OnNameChange -> {
                _uiState.update { it.copy(name = event.text) }
            }

            is FirstScreenEvent.OnPalindromeChange -> {
                _uiState.update { it.copy(palindrome = event.text, isPalindrome = null) }
            }

            is FirstScreenEvent.OnPalindromCheckClicked -> {
                checkPalindromeText()
            }

            is FirstScreenEvent.OnCheckDialogDismissed -> {
                _uiState.update { it.copy(showCheckDialog = false) }
            }

            is FirstScreenEvent.OnNextScreenClicked -> {
                onNextClicked()
            }

            is FirstScreenEvent.OnNextErrorDismissed -> {
                _uiState.update { it.copy(showErrorDialog = false) }
            }
        }
    }

    private fun checkPalindromeText() {
        val text = _uiState.value.palindrome.replace("\\s".toRegex(), "")
        if (text.isEmpty()) {
            _uiState.update { it.copy(isPalindrome = false) }
            return
        }
        val isPalindrome = text.equals(text.reversed(), ignoreCase = true)
        _uiState.update {
            it.copy(
                isPalindrome = isPalindrome,
                showCheckDialog = true
            )
        }
    }

    private val _navigationEvent = Channel<NavigationEvent>()
    val navigationEvents = _navigationEvent.receiveAsFlow()

    private fun onNextClicked() {
        viewModelScope.launch {
            val name = _uiState.value.name
            if (name.isNotBlank()) {
                _navigationEvent.send(NavigationEvent.NavigateToSecondScreen(name))
            } else {
                _uiState.update { it.copy(showErrorDialog = true) }
            }
        }
    }

}

data class FirstScreenUiState(
    val name: String = "",
    val palindrome: String = "",
    val isPalindrome: Boolean? = null,
    val showCheckDialog: Boolean = false,
    val showErrorDialog: Boolean = false
)

sealed interface FirstScreenEvent {
    data class OnNameChange(val text: String) : FirstScreenEvent
    data class OnPalindromeChange(val text: String) : FirstScreenEvent
    object OnPalindromCheckClicked : FirstScreenEvent
    object OnCheckDialogDismissed : FirstScreenEvent
    object OnNextScreenClicked : FirstScreenEvent
    object OnNextErrorDismissed : FirstScreenEvent
}

sealed interface NavigationEvent {
    data class NavigateToSecondScreen(val name: String) : NavigationEvent
}
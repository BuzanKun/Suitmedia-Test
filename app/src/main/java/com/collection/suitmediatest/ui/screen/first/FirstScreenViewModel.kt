package com.collection.suitmediatest.ui.screen.first

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
}

data class FirstScreenUiState(
    val name: String = "",
    val palindrome: String = "",
    val isPalindrome: Boolean? = null,
    val showCheckDialog: Boolean = false
)

sealed interface FirstScreenEvent {
    data class OnNameChange(val text: String) : FirstScreenEvent
    data class OnPalindromeChange(val text: String) : FirstScreenEvent
    object OnPalindromCheckClicked : FirstScreenEvent
    object OnCheckDialogDismissed : FirstScreenEvent
    object OnNextScreenClicked : FirstScreenEvent
}
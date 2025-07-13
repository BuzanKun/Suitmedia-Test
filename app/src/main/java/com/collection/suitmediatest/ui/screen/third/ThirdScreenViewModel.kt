package com.collection.suitmediatest.ui.screen.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collection.suitmediatest.data.remote.UserRepository
import com.collection.suitmediatest.data.remote.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class ThirdScreenViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ThirdScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUsers()
    }

    fun onEvent(event: ThirdScreenEvent) {
        when (event) {
            is ThirdScreenEvent.LoadNextPage -> getUsers(isLoadMore = true)
            is ThirdScreenEvent.Refresh -> getUsers(isRefresh = true)
        }
    }

    fun getUsers(isRefresh: Boolean = false, isLoadMore: Boolean = false) {
        val currentState = _uiState.value
        if (currentState.isLoading || currentState.isLoadingMore || (currentState.isLastPage && !isRefresh)) {
            return
        }

        viewModelScope.launch {
            val pageToLoad = if (isRefresh) 1 else currentState.currentPage

            _uiState.update {
                it.copy(
                    isLoading = !isLoadMore,
                    isLoadingMore = isLoadMore,
                    users = if (isRefresh) emptyList() else it.users,
                    currentPage = if (isRefresh) 1 else it.currentPage,
                    isLastPage = if (isRefresh) false else it.isLastPage
                )
            }

            userRepository.getUsers(page = pageToLoad, perPage = 10)
                .onSuccess { newUsers ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            users = it.users + newUsers,
                            currentPage = it.currentPage + 1,
                            isLastPage = newUsers.isEmpty()
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            error = error.message ?: "An unknown error occurred"
                        )
                    }
                }
        }
    }
}

data class ThirdScreenUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1,
    val isLastPage: Boolean = false
)

sealed interface ThirdScreenEvent {
    object LoadNextPage : ThirdScreenEvent
    object Refresh : ThirdScreenEvent
}
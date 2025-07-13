package com.collection.suitmediatest.ui.screen.third

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.collection.suitmediatest.ViewModelFactory
import com.collection.suitmediatest.components.UserItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(
    navController: NavController,
    viewModel: ThirdScreenViewModel = viewModel(factory = ViewModelFactory.getInstance())
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex != null &&
                    lastVisibleItemIndex >= uiState.users.size - 1 &&
                    !uiState.isLoadingMore &&
                    !uiState.isLastPage
                ) {
                    viewModel.onEvent(ThirdScreenEvent.LoadNextPage)
                }
            }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Third Screen",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.onEvent(ThirdScreenEvent.Refresh) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.isLoading && uiState.users.isEmpty()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                    )
                } else if (uiState.users.isEmpty()) {
                    Text(
                        "No users found.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
                        items(uiState.users) { user ->
                            UserItem(user = user, onClick = {
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("selected_username", user.fullName)
                                navController.popBackStack()
                            })
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                        if (uiState.isLoadingMore) {
                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentWidth(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
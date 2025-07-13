package com.collection.suitmediatest.ui.screen.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.collection.suitmediatest.R
import com.collection.suitmediatest.components.CustomButton
import com.collection.suitmediatest.components.CustomTextField

@Composable
fun FirstScreen(
    viewModel: FirstScreenViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.showCheckDialog) {
        val checkResult = if (uiState.isPalindrome == true) {
            " is a Palindrome"
        } else {
            " is NOT a Palindrome"
        }

        Dialog(
            onDismissRequest = { viewModel.onEvent(FirstScreenEvent.OnCheckDialogDismissed) }
        ) {
            Card(
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .widthIn(max = 280.dp)
                        .padding(16.dp),
                ) {
                    Text(
                        text = uiState.palindrome
                    )
                    Text(
                        text = checkResult
                    )
                }
            }
        }
    }

    val scrollState = rememberScrollState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.add_photo),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(48.dp))
                CustomTextField(
                    value = uiState.name,
                    onValueChange = {
                        viewModel.onEvent(FirstScreenEvent.OnNameChange(it))
                    },
                    placeholder = "Name"
                )
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(
                    value = uiState.palindrome,
                    onValueChange = {
                        viewModel.onEvent(FirstScreenEvent.OnPalindromeChange(it))
                    },
                    placeholder = "Palindrome"
                )
                Spacer(modifier = Modifier.height(36.dp))
                CustomButton(
                    text = "CHECK",
                    onClick = { viewModel.onEvent(FirstScreenEvent.OnPalindromCheckClicked) },
                )
                Spacer(modifier = Modifier.height(4.dp))
                CustomButton(
                    text = "NEXT",
                    onClick = { viewModel.onEvent(FirstScreenEvent.OnNextScreenClicked) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun FirstScreenPreview() {
    FirstScreen()
}
package com.collection.suitmediatest.ui.screen.second

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.collection.suitmediatest.R
import com.collection.suitmediatest.components.CustomButton
import com.collection.suitmediatest.navigation.Screens
import com.collection.suitmediatest.ui.theme.Teal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(
    navController: NavController,
    name: String,
    selectedUsername: String?
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Second Screen",
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
    ) { innerpadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxHeight()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.add_photo),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Teal),
                    modifier = Modifier.size(120.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Welcome", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Text(
                text = selectedUsername ?: "Selected User Name",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            CustomButton(
                text = "Choose a User",
                onClick = {
                    navController.navigate(Screens.ThirdScreen.route)
                },
            )
        }
    }
}

@Preview
@Composable
private fun SecondScreenPreview() {
    SecondScreen(
        navController = rememberNavController(),
        name = "Rizky",
        selectedUsername = "Buzank"
    )
}
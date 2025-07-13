package com.collection.suitmediatest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.collection.suitmediatest.data.remote.model.User
import com.collection.suitmediatest.ui.theme.SuitmediaTestTheme

@Composable
fun UserItem(user: User, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(4.dp))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(60.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = user.fullName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = user.email.toString(),
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
private fun UserItemPreview() {
    SuitmediaTestTheme {
        UserItem(
            User(
                id = 1,
                avatarUrl = "https://reqres.in/img/faces/1-image.jpg",
                fullName = "Buzank",
                email = "123@email.com",
            ),
            onClick = {}
        )
    }
}
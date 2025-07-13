package com.collection.suitmediatest.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.collection.suitmediatest.R

@Composable
fun CustomBasicDialog(onDismissRequest: () -> Unit, text: String, icon: Int) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 280.dp)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = text
                )
            }
        }
    }
}

@Preview
@Composable
private fun CustomBasicDialogPreview() {
    CustomBasicDialog(
        onDismissRequest = {},
        text = "Buzank is not a Palindrome",
        icon = R.drawable.info_outline_24
    )
}
package com.collection.suitmediatest.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.collection.suitmediatest.ui.theme.Teal

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Teal),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .widthIn(max = 320.dp)
            .fillMaxWidth()
    ) {
        Text(text = text, fontWeight = FontWeight.SemiBold)
    }
}

@Preview
@Composable
private fun CustomButtonPreview() {
    CustomButton(
        text = "Test",
        onClick = { }
    )
}
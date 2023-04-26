package com.example.noteit.features_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRadioButton(
    text: String,
    select: Boolean = false,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically )
    {
        RadioButton(
            selected = select, onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFFF0F4C3),
                unselectedColor =Color(0xFFAFE6FF),
            )
        )

        Text(text = text,  style = MaterialTheme.typography.caption, color = Color(0xFFDEFFDF),)
    }
}
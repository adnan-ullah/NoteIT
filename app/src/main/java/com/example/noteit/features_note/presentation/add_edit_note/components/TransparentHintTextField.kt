package com.example.noteit.features_note.presentation.add_edit_note.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    isHintVisible: Boolean = true,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    textStyle: TextStyle = TextStyle(),
    onValueChange: (String) -> Unit,
    onfocusStateChange: (FocusState) -> Unit,

    ) {

    BasicTextField(
        value = text,
        onValueChange =
            onValueChange
        ,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                onfocusStateChange(it)
            },
        singleLine = singleLine,
        textStyle =textStyle
    )
    {
      if(isHintVisible)
      {
          Text(text = hint, style = textStyle, color = Color.DarkGray)
      }
        else
          Text(text = text, style = textStyle, color = Color.Black)

    }

}
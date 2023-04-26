package com.example.noteit.features_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteit.features_note.domain.utils.NoteOrder
import com.example.noteit.features_note.domain.utils.OrderType

@Composable
fun OrderSection(
    modifier: Modifier= Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange : (NoteOrder)->Unit
)
{
    Column(modifier = Modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Title", select = noteOrder is NoteOrder.Title,
                onSelect = { onOrderChange(NoteOrder.Title(noteOrder.orderType))})
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Date", select = noteOrder is NoteOrder.Date,
                onSelect = { onOrderChange(NoteOrder.Date(noteOrder.orderType))})
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Color", select = noteOrder is NoteOrder.Color,
                onSelect = { onOrderChange(NoteOrder.Color(noteOrder.orderType))})

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Ascending", select = noteOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(noteOrder.copy(OrderType.Ascending))})
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Descending", select = noteOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(noteOrder.copy(OrderType.Descending))})


        }


    }

}
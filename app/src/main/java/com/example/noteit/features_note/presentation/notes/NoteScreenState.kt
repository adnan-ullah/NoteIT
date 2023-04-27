package com.example.noteit.features_note.presentation.notes

import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.domain.utils.NoteOrder
import com.example.noteit.features_note.domain.utils.OrderType

data class NoteScreenState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val profileInfoToggleButton:Boolean = false,
    val favouriteNoteList: List<Note> = emptyList()
)

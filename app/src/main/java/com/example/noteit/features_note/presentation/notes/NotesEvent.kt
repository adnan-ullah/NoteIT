package com.example.noteit.features_note.presentation.notes


import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.domain.utils.NoteOrder

sealed class NotesEvent{

    data class order(val noteOrder: NoteOrder):NotesEvent()
    data class deleteNote(val note: Note):NotesEvent()
    data class addToFavourite(val note:Note):NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSelection:NotesEvent()
    object profileInfoToggleButton:NotesEvent()

}

package com.example.noteit.features_note.presentation.add_edit_note



data class NoteTextFieldState(
    val text: String ="",
    val hint: String="",
    val hintVisibility: Boolean = false
) {
}
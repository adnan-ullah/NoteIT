package com.example.noteit.features_note.presentation.utils

sealed class Screen(val route:String){
    object NoteScreen:Screen("note_Screen")
    object AddEditNoteScreen:Screen("add_edit_note_screen")

}

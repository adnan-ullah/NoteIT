package com.example.noteit.features_note.presentation.utils

sealed class Screen(val route:String){
    object NoteScreen:Screen("note_Screen")
    object AddEditNoteScreen:Screen("add_edit_note_screen")
    object ProfileScreen:Screen("profile_screen")
    object FavouriteNotes:Screen("favourite_notes")
    object HomePage:Screen("home_page")

    object RootPage:Screen("root_page")

}

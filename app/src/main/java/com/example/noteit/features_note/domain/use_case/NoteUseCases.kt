package com.example.noteit.features_note.domain.use_case

import java.util.Objects

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote,
    val addToFavourite: AddFavourite
)

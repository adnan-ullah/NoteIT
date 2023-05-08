package com.example.noteit.features_note.domain.use_case.NoteUseCases

import com.example.noteit.features_note.domain.use_case.NoteUseCases.*

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote,
    val addToFavourite: AddFavourite,
    val getNotesFromRemote: GetNotesFromRemote
)

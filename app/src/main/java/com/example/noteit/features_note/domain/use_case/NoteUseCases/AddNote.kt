package com.example.noteit.features_note.domain.use_case.NoteUseCases

import com.example.noteit.features_note.domain.model.InvalideNoteException
import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.domain.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) {
        @Throws(InvalideNoteException::class)
        if (note.title.isBlank()) {
            throw InvalideNoteException("Note Title can't be empty")
        }
        if (note.content.isBlank()) {
            throw InvalideNoteException("Note Content can't be empty")
        }

        noteRepository.insertNote(note)

    }
}


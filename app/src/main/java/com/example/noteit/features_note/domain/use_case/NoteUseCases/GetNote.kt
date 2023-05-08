package com.example.noteit.features_note.domain.use_case.NoteUseCases

import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.domain.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id:Int) : Note?
    {
        return noteRepository.getNoteById(id)
    }
}
package com.example.noteit.features_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.domain.use_case.NoteUseCases
import com.example.noteit.features_note.domain.utils.NoteOrder
import com.example.noteit.features_note.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val useCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeleteNote: Note? = null
    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Ascending))
    }

    fun onEvent(notesEvent: NotesEvent) {
        when (notesEvent) {
            is NotesEvent.order -> {
                if (state.value.noteOrder::class == notesEvent.noteOrder::class &&
                    state.value.noteOrder.orderType == notesEvent.noteOrder.orderType
                ) {
                    return
                }
                getNotes(notesEvent.noteOrder)
            }
            is NotesEvent.deleteNote -> {
                viewModelScope.launch {
                    useCases.deleteNote(notesEvent.note)
                    recentlyDeleteNote = notesEvent.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    useCases.addNote(recentlyDeleteNote ?: return@launch)
                    recentlyDeleteNote = null
                }
            }
            is NotesEvent.ToggleOrderSelection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = useCases.getNotes(noteOrder).onEach {notes ->
            _state.value = state.value.copy(
                notes = notes,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }
}
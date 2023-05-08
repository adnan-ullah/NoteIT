package com.example.noteit.features_note.presentation.notes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteit.features_note.common.Resource
import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.domain.model.ProductsItem
import com.example.noteit.features_note.domain.use_case.NoteUseCases.NoteUseCases
import com.example.noteit.features_note.domain.utils.NoteOrder
import com.example.noteit.features_note.domain.utils.OrderType
import com.example.noteit.features_note.presentation.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val useCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(NoteScreenState())
    val state: State<NoteScreenState> = _state


    private var recentlyDeleteNote: Note? = null
    private var getNotesJob: Job? = null

    init {


        useCases.getNotesFromRemote().onEach {
            when(it) {
                is Resource.Success -> {
                    val arrayList =it.data!!
                    _state.value = state.value.copy(
                        favouriteNoteList = arrayList.products as List<ProductsItem>
                    )
                    Log.d("isSuccess", "YES")
                }
                is Resource.Error -> {
                    Log.d("isFailed", "NO")
                }
                is Resource.Loading -> {
                    Log.d("isLoading", "YES")
                }
            }
        }.launchIn(viewModelScope)


        savedStateHandle.get<String>("screen_name")?.let {
            if(it==Screen.NoteScreen.route)
             getNotes(NoteOrder.Date(OrderType.Ascending))

        }

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
            is NotesEvent.profileInfoToggleButton ->
            {
                _state.value = state.value.copy(
                    profileInfoToggleButton =  !state.value.profileInfoToggleButton
                )
            }
            is NotesEvent.addToFavourite ->{

            }

        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = useCases.getNotes(noteOrder).onEach {notes ->
            _state.value = state.value.copy(
                notes= notes,
                noteOrder = noteOrder

            )
        }.launchIn(viewModelScope)
        Log.d("Called", "Called this viewmodel")
    }
}
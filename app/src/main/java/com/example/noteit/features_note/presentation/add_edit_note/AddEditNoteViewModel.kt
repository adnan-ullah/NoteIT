package com.example.noteit.features_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteit.features_note.domain.model.InvalideNoteException
import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.domain.use_case.NoteUseCases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddEditNoteViewModel  @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter title..."
    ))
    val noteTitle: State<NoteTextFieldState>  = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter Content..."
    ))
    val noteContent: State<NoteTextFieldState>  = _noteContent

    private val _noteColor = mutableStateOf<Int>(Note.noteColors.random().toArgb())
    val noteColor:State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("noteId")?.let { note->
            if(note != -1)
            {
                viewModelScope.launch {
                    noteUseCases.getNote(note)?.also {
                        note->
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            hintVisibility = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            hintVisibility = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

     fun onEvent(addEditNoteEvent: AddEditNoteEvent)
    {
        when(addEditNoteEvent)
        {
            is AddEditNoteEvent.EnteredTitle->{
                _noteTitle.value = noteTitle.value.copy(
                    text = addEditNoteEvent.title
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus->{
                _noteTitle.value = noteTitle.value.copy(
                    hintVisibility = !addEditNoteEvent.focusTitle.isFocused && noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent->{
                _noteContent.value = noteContent.value.copy(
                    text = addEditNoteEvent.content
                )
            }
            is AddEditNoteEvent.ChangeContentFocus->{
                _noteContent.value = noteContent.value.copy(
                    hintVisibility = !addEditNoteEvent.focusContent.isFocused && noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor->{
             _noteColor.value = addEditNoteEvent.color
            }

            is AddEditNoteEvent.SaveNote->{
                try {
                    viewModelScope.launch {
                        noteUseCases.addNote(
                            Note(title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = Random.nextInt()
                            )
                        )

                        _eventFlow.emit(UiEvent.SaveNote)
                    }
                }
                catch (e: InvalideNoteException)
                {
                   // _eventFlow.emit(UiEvent.ShowSnackbar(message= e.message?:"Could not save"))

                }
            }
        }
    }

    sealed  class UiEvent()
    {
        data class ShowSnackbar(val message:String):UiEvent()
        object SaveNote:UiEvent()


    }
}
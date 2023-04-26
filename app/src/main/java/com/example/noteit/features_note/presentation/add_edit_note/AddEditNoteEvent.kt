package com.example.noteit.features_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color

sealed class AddEditNoteEvent {
    data class EnteredTitle(val title :String): AddEditNoteEvent()
    data class EnteredContent(val content:String):AddEditNoteEvent()
    data class ChangeTitleFocus(val focusTitle: FocusState) : AddEditNoteEvent()
    data class ChangeContentFocus(val focusContent: FocusState):AddEditNoteEvent()
    data class ChangeColor(val color: Int):AddEditNoteEvent()
    object SaveNote:AddEditNoteEvent()
}
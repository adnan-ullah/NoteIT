package com.example.noteit.features_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteit.features_note.data.data_source.NoteDatabase

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
)
{
    companion object {
        val noteColors = listOf(Color.Red,Color.Green, Color.Yellow, Color.Cyan, Color.Magenta)
    }
}

class InvalideNoteException(message:String) : Exception(message)
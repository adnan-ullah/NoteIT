package com.example.noteit.di

import android.app.Application
import androidx.room.Room
import com.example.noteit.features_note.data.data_source.NoteDatabase
import com.example.noteit.features_note.data.repository.NoteRepositoryImpl
import com.example.noteit.features_note.domain.repository.NoteRepository
import com.example.noteit.features_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase (app:Application):NoteDatabase{
        return Room.databaseBuilder(app,NoteDatabase::class.java,NoteDatabase.NOTE_DATABSE)
            .addMigrations()
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRepository(noteDatabase: NoteDatabase):NoteRepository
    {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideUseCases(noteRepository: NoteRepository):NoteUseCases
    {
        return NoteUseCases(getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository),
            addToFavourite = AddFavourite(noteRepository)


            )
    }
}
package com.example.noteit.di

import android.app.Application
import androidx.room.Room
import com.example.noteit.features_note.common.Constant
import com.example.noteit.features_note.data.data_source.NoteDatabase
import com.example.noteit.features_note.data.remote.ApiInterface
import com.example.noteit.features_note.data.repository.NetworkRepositoryImpl
import com.example.noteit.features_note.data.repository.NoteRepositoryImpl
import com.example.noteit.features_note.domain.repository.NetworkRepository
import com.example.noteit.features_note.domain.repository.NoteRepository
import com.example.noteit.features_note.domain.use_case.*
import com.example.noteit.features_note.domain.use_case.NoteUseCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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


//retrofit providers>>
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun getApiInterface(okHttpClient: OkHttpClient):ApiInterface {

        return Retrofit.Builder()
            .baseUrl(Constant.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
    @Provides
    @Singleton
    fun getNetworkRepositary(apiInterface: ApiInterface):NetworkRepository {
        return NetworkRepositoryImpl(apiInterface)
    }
//<<

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository,
                            networkRepository: NetworkRepository): NoteUseCases
    {
        return NoteUseCases(getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository),
            addToFavourite = AddFavourite(noteRepository),
            getNotesFromRemote = GetNotesFromRemote(networkRepositary =networkRepository )
        )
    }
}
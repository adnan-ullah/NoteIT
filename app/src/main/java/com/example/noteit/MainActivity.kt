package com.example.noteit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteit.features_note.presentation.add_edit_note.components.AddEditNoteScreen
import com.example.noteit.features_note.presentation.notes.NotesViewModel
import com.example.noteit.features_note.presentation.notes.components.DefaultRadioButton
import com.example.noteit.features_note.presentation.notes.components.NoteItem
import com.example.noteit.features_note.presentation.notes.components.NoteScreen
import com.example.noteit.features_note.presentation.notes.components.OrderSection
import com.example.noteit.features_note.presentation.utils.Screen
import com.example.noteit.ui.theme.NoteITTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            NoteITTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    
                    NavHost(navController = navController, startDestination =Screen.NoteScreen.route )
                    {
                        composable(route = Screen.NoteScreen.route,
                        )
                        {
                            NoteScreen(navController =navController)
                        }

                        composable(route = Screen.AddEditNoteScreen.route+"?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(
                            navArgument(name = "noteId"){
                                type= NavType.IntType
                                defaultValue=-1
                                                        },
                            navArgument(name = "noteColor"){
                                type= NavType.IntType
                                defaultValue= -1
                            },


                        )
                            ){
                            val color = it.arguments?.getInt("noteColor")?: -1
                            AddEditNoteScreen(navController = navController, noteColor =color)
                        }
                    }

                }
            }
        }
    }
}


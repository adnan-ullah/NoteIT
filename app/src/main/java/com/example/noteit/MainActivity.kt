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
import com.example.noteit.features_note.presentation.notes.NoteScreenState
import com.example.noteit.features_note.presentation.notes.NotesViewModel
import com.example.noteit.features_note.presentation.notes.components.*
import com.example.noteit.features_note.presentation.products.components.HomePage
import com.example.noteit.features_note.presentation.products.components.RootPage
import com.example.noteit.features_note.presentation.profile.ProfileInfo
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
                    
                    NavHost(navController = navController, startDestination =Screen.RootPage.route)
                    {
                        composable(route = Screen.NoteScreen.route+"?screen_name=${Screen.NoteScreen.route}",
                            arguments = listOf(
                                navArgument(name = "screen_name"){
                                    type= NavType.StringType
                                    defaultValue=Screen.NoteScreen.route
                                },
                            )

                        )
                        {
                            NoteScreen(navController =navController)
                        }

                        composable(route = Screen.ProfileScreen.route){
                            ProfileInfo(modifier = Modifier)
                        }
                        composable(route = Screen.FavouriteNotes.route){
                            FavouriteScreen( navController= navController )
                        }
                        composable(route = Screen.HomePage.route){
                            HomePage( navController= navController )
                        }
                        composable(route = Screen.RootPage.route){
                            RootPage( navController = navController )
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


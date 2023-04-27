package com.example.noteit.features_note.presentation.notes.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.presentation.notes.NotesEvent
import com.example.noteit.features_note.presentation.notes.NotesViewModel
import com.example.noteit.features_note.presentation.profile.ProfileInfo
import com.example.noteit.features_note.presentation.utils.Screen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavouriteScreen(
    modifier:Modifier= Modifier,
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Log.d("TotalNote", state.favouriteNoteList.size.toString())

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        backgroundColor = Color(0xFF212C47),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddEditNoteScreen.route) },
                backgroundColor = Color(0xFFFFFFBF),
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        scaffoldState = scaffoldState

    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()

        ) {




            AnimatedVisibility(
                visible = state.profileInfoToggleButton,
                enter = fadeIn() + slideInVertically(), exit = fadeOut() + slideOutVertically()
            ) {
                ProfileInfo(modifier = Modifier)
            }





            LazyColumn()
            {
                items(state.favouriteNoteList) { note: Note ->
                    NoteItem(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditNoteScreen.route +
                                            "?noteId=${note.id}&noteColor=${note.color}"
                                )

                            },
                        note = note,
                        onDeleteClick = {

                        },
                        onFavouriteClick = {

                        }

                    )
                }
            }


        }
    }

}
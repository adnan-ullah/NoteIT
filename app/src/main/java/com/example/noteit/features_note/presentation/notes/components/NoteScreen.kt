package com.example.noteit.features_note.presentation.notes.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.noteit.features_note.presentation.utils.Screen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalAnimationApi
fun NoteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NotesViewModel= hiltViewModel()
) {
    val state = viewModel.state.value
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

    ) {it->
        Column(
            modifier = Modifier

                .padding(20.dp)
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                )

                IconButton(onClick = {
                    viewModel.onEvent(NotesEvent.ToggleOrderSelection)
                }) {
                    Icon(imageVector = Icons.Default.List, contentDescription = "Sort note")

                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxSize()) {

                AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                    enter = fadeIn() + slideInVertically(), exit = fadeOut() + slideOutVertically()
                ) {
                    OrderSection(
                        modifier = Modifier.fillMaxWidth(),
                        noteOrder = state.noteOrder,
                        onOrderChange = { viewModel.onEvent(NotesEvent.order(it)) })
                }

                Spacer(modifier = Modifier.height(16.dp))


                LazyColumn(modifier = Modifier.fillMaxSize())
                {
                    items(state.notes) { note: Note ->
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
                            onDeleteClick = { viewModel.onEvent(NotesEvent.deleteNote(note))
                            scope.launch {
                                var result = scaffoldState.snackbarHostState.showSnackbar("Note has been deleted.",actionLabel = "Undo")
                           if(result==SnackbarResult.ActionPerformed)
                           {
                               viewModel.onEvent(NotesEvent.RestoreNote)
                           }
                            }
                            }

                        )
                    }
                }


            }
        }


    }

}
package com.example.noteit.features_note.presentation.products.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteit.features_note.domain.model.ProductsItem
import com.example.noteit.features_note.presentation.notes.NotesViewModel
import com.example.noteit.features_note.presentation.utils.components.LazyHorizontalStaggeredView
import com.example.noteit.features_note.presentation.utils.components.LazyVerticalStaggeredView

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RootPage(navController: NavController, viewModel: NotesViewModel= hiltViewModel()) {


    val state = viewModel.state.value
    Log.d("Favourite_Note", "RootPage: ${state.favouriteNoteList.size}")
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.padding(8.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween)
            {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.List, contentDescription = "List")
                }

                TextField(
                    value = "",
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFEBEBEB),
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.Red,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {

                    },
                    shape = RoundedCornerShape(80.dp),
                    singleLine = true,
                    trailingIcon = {

                    }
                )

                Row()
                {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "List")
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "List")
                    }

                }

            }
            Spacer(modifier = Modifier.height(20.dp))


            LazyVerticalStaggeredView(
                columns = 2,
                label = "Adnan",
                containerHeight = 300.dp,
                cardHeight = 220.dp,
                items = state.favouriteNoteList,
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyHorizontalStaggeredView(
                rows = 1,
                label = "Your Restaurents",
                containerHeight = 300.dp,
                cardHeight = 300.dp,
                cardWidth = 300.dp,
                items = state.favouriteNoteList,
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyHorizontalStaggeredView(
                rows = 2,
                label = "Your Restaurents",
                containerHeight = 300.dp,
                cardHeight = 80.dp,
                cardWidth = 80.dp,
                items = state.favouriteNoteList,
            )


            Spacer(modifier = Modifier.height(40.dp))

            LazyHorizontalStaggeredView(
                rows = 1,
                label = "Your daily deals",
                containerHeight = 200.dp,
                cardHeight = 180.dp,
                cardWidth = 140.dp,
                items = state.favouriteNoteList,
            )



            Spacer(modifier = Modifier.height(20.dp))
            LazyHorizontalStaggeredView(
                rows = 1,
                label = "Shops",
                containerHeight = 100.dp,
                cardHeight = 80.dp,
                cardWidth = 80.dp,
                items = state.favouriteNoteList,
            )



            Spacer(modifier = Modifier.height(40.dp))
            LazyHorizontalStaggeredView(
                rows = 2,
                label = "pandamart",
                containerHeight = 300.dp,
                cardHeight = 80.dp,
                cardWidth = 80.dp,
                items = state.favouriteNoteList,
            )




        }


    }
}
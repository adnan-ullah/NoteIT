package com.example.noteit.features_note.presentation.utils.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.noteit.features_note.domain.model.ProductsItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyVerticalStaggeredView(
    containerHeight: Dp,
    cardHeight:Dp,
    items:List<ProductsItem>,
    columns: Int = 1,
    label: String = "Label",
){
    Box(modifier = Modifier
        .height(containerHeight)
        .fillMaxWidth()) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(columns),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                items(items.size) { item ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = Color(0xFFC8E6C9)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter("${items.get(item).images?.get(0)}"),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(cardHeight)
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
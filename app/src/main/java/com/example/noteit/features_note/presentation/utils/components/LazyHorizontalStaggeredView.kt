package com.example.noteit.features_note.presentation.utils.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.noteit.features_note.domain.model.ProductsItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyHorizontalStaggeredView(
    containerHeight: Dp,
    items: List<ProductsItem> = emptyList(),
    cardHeight: Dp,
    cardWidth: Dp,
    rows: Int = 1,
    label: String = "Label"
    ){
    Box(modifier = Modifier
        .height(containerHeight)
        .fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "$label", style = TextStyle(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(10.dp))
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Fixed(rows),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),


                content = {
                    items(items.size) { item ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                backgroundColor = Color(0xFF313131)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(cardWidth)
                                        .height(cardHeight)
                                        .padding(16.dp)
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter("${items.get(item).images?.get(0)}"),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.size(cardHeight)
                                    )
                                }
                            }
                            Text("Label",style= TextStyle(color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold) )
                        }


                    }
                },

                )
        }
    }
}
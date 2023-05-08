package com.example.noteit.features_note.presentation.products.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import coil.compose.rememberAsyncImagePainter
import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.domain.model.ProductsItem

@Composable
fun ProductItem(

                modifier: Modifier,
                productItem: ProductsItem,
                cutCornerSize: Dp = 30.dp,
                cornerRadius: Dp = 10.dp,
                onDeleteClick : () -> Unit,
                onFavouriteClick: () -> Unit
            ) {

                Box(modifier = modifier.padding(vertical = 10.dp))
                {
                    Canvas(
                        modifier = Modifier.matchParentSize(),
                    )
                    {
                        val clipPath = Path().apply {
                            lineTo(size.width - cutCornerSize.toPx(), 0f)
                            lineTo(size.width, cutCornerSize.toPx())
                            lineTo(size.width, size.height)
                            lineTo(0f, size.height)
                            close()
                        }

                        clipPath(clipPath) {
                            drawRoundRect(
                                color = Color(0xFF333331),
                                size = size,
                                cornerRadius = CornerRadius(cornerRadius.toPx())
                            )
                            drawRoundRect(
                                color = Color.Red,
                                topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                                size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                                cornerRadius = CornerRadius(cornerRadius.toPx())
                            )

                        }

                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                            .padding(end = 32.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter("${productItem.images?.get(0)}"),
                            contentDescription = null,
                            modifier = Modifier.size(128.dp)
                        )
                        Text(
                            text = productItem.title!!,
                            style = MaterialTheme.typography.h6,
                            color = Color(0xFFFFFFFF),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = productItem.description!!,
                            style = MaterialTheme.typography.caption,
                            color = Color(0xFFFFCCBC),
                            maxLines = 10,
                            overflow = TextOverflow.Ellipsis
                        )

                        Row(modifier= Modifier.align(Alignment.End))
                        {
                            IconButton(onClick = onDeleteClick ,

                                ) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note", tint = Color.White)
                            }
                            IconButton(onClick = onFavouriteClick ,
                            ) {
                                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Favorite Note",tint = Color.White)
                            }
                        }
                    }

                }

            }


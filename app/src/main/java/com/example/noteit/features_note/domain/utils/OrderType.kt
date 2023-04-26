package com.example.noteit.features_note.domain.utils

sealed class OrderType
{
    object Ascending: OrderType()
    object Descending: OrderType()

}

package com.example.noteit.features_note.domain.repository

import com.example.noteit.features_note.domain.model.Note
import com.example.noteit.features_note.domain.model.Products
import com.example.noteit.features_note.domain.model.ProductsItem

interface NetworkRepository {
        suspend fun getProductsFromRemote():Products
}
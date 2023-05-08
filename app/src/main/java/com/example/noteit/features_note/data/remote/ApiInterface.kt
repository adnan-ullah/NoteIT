package com.example.noteit.features_note.data.remote

import com.example.noteit.features_note.domain.model.Products
import com.example.noteit.features_note.domain.model.ProductsItem
import retrofit2.http.GET

interface ApiInterface {
    @GET("/products")
    suspend fun getProductsFromRemote(): Products
}
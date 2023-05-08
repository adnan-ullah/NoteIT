package com.example.noteit.features_note.data.repository

import com.example.noteit.features_note.data.remote.ApiInterface
import com.example.noteit.features_note.domain.model.Products
import com.example.noteit.features_note.domain.model.ProductsItem
import com.example.noteit.features_note.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl  @Inject constructor(val apiInterface: ApiInterface):NetworkRepository {
    override suspend fun getProductsFromRemote():Products {
        return apiInterface.getProductsFromRemote()
    }
}
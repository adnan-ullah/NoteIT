package com.example.noteit.features_note.domain.use_case.NoteUseCases

import com.example.noteit.features_note.common.Resource
import com.example.noteit.features_note.domain.model.Products
import com.example.noteit.features_note.domain.model.ProductsItem
import com.example.noteit.features_note.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetNotesFromRemote @Inject constructor(
    val networkRepositary: NetworkRepository
) {
    operator fun invoke(): Flow<Resource<Products>>  = flow {
        try {
            val result = networkRepositary.getProductsFromRemote()

            emit(Resource.Success<Products>(result))
        } catch (e: Exception) {
            emit(Resource.Error<Products>("Connection Failed"))
        }
    }
}
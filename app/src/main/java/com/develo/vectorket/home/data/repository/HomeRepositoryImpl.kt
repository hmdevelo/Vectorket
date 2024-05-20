package com.develo.vectorket.home.data.repository

import com.develo.vectorket.core.data.util.FIELD_CREATED_AT
import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.core.domain.model.Response.Failure
import com.develo.vectorket.core.domain.model.Response.Success
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.home.domain.alias.LatestVectorResponse
import com.develo.vectorket.home.domain.repository.HomeRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query.Direction.DESCENDING
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class HomeRepositoryImpl @Inject constructor(
    @Named(VECTORS_COLLECTION)
    private val vectors: CollectionReference
) : HomeRepository {

    override suspend fun getLatestVector(): LatestVectorResponse {
        return try {
            val result = vectors.orderBy(FIELD_CREATED_AT, DESCENDING)
                .limit(1)
                .get()
                .await()
            Success(result.first().toObject(Vector::class.java))
        } catch (e: Exception) {
            Failure(e)
        }
    }
}
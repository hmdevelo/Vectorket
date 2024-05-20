package com.develo.vectorket.feed.data.repository

import com.develo.vectorket.core.data.util.FIELD_CREATED_AT
import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.feed.domain.repository.FeedRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query.Direction
import javax.inject.Inject
import javax.inject.Named

class FeedRepositoryImpl @Inject constructor(
    @Named(VECTORS_COLLECTION)
    private val vectors: CollectionReference
) : FeedRepository {
    override fun getLatestVectorsQuery(limit: Long) =
        vectors.orderBy(FIELD_CREATED_AT, Direction.DESCENDING)
            .limit(limit)
}
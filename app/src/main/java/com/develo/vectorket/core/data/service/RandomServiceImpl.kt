package com.develo.vectorket.core.data.service

import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.core.domain.service.RandomService
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query
import javax.inject.Inject
import javax.inject.Named

class RandomServiceImpl @Inject constructor(
    @Named(VECTORS_COLLECTION)
    private val vectors: CollectionReference
) : RandomService {

    override fun getId() = vectors.document().id

    override fun buildQuery(id: String, limit: Long): Query {
        return if (id.startsWith(char = 'z', ignoreCase = true))
            vectors.whereLessThanOrEqualTo(FieldPath.documentId(), id).limit(limit)
        else
            vectors.whereGreaterThanOrEqualTo(FieldPath.documentId(), id).limit(limit)
    }
}
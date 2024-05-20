package com.develo.vectorket.core.data.query

import com.develo.vectorket.core.data.util.FIELD_CREATED_AT
import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import javax.inject.Named

class QueryBuilder(
    @Named(VECTORS_COLLECTION)
    private val vectors: CollectionReference
) {
    fun build(fieldPath: FieldPath, values: List<String>) = if (values.isEmpty())
        vectors.whereEqualTo(FIELD_CREATED_AT, null)
    else vectors.whereIn(fieldPath, values)
}
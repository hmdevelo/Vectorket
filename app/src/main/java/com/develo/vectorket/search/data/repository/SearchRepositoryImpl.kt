package com.develo.vectorket.search.data.repository

import com.develo.vectorket.core.data.util.FIELD_KEYWORDS
import com.develo.vectorket.core.data.util.PAGE_SIZE
import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.core.domain.service.RandomService
import com.develo.vectorket.search.domain.repository.SearchRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import javax.inject.Inject
import javax.inject.Named

class SearchRepositoryImpl @Inject constructor(
    @Named(VECTORS_COLLECTION)
    private val vectors: CollectionReference,
    private val randomService: RandomService
) : SearchRepository {

    private val id = randomService.getId()

    override fun buildQuery(criteria: String): Query {
        return if (criteria.isBlank())
            randomService.buildQuery(id, PAGE_SIZE)
        else vectors.whereArrayContains(FIELD_KEYWORDS, criteria).limit(PAGE_SIZE)
    }
}
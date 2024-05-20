package com.develo.vectorket.search.domain.repository

import com.google.firebase.firestore.Query

interface SearchRepository {

    fun buildQuery(criteria: String): Query

}
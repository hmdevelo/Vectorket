package com.develo.vectorket.core.domain.service

import com.google.firebase.firestore.Query

interface RandomService {

    fun getId(): String

    fun buildQuery(id: String, limit: Long): Query
}
package com.develo.vectorket.feed.domain.repository

import com.google.firebase.firestore.Query

interface FeedRepository {
    fun getLatestVectorsQuery(limit: Long): Query
}
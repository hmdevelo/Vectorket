package com.develo.vectorket.blog.domain.model

import androidx.compose.runtime.Immutable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

@Immutable
data class Blog(
    @DocumentId val uid: String = "",
    val title: String = "",
    val createdAt: Timestamp = Timestamp.now(),
    val imageUrl: String = "",
    val description: String = "",
    val author: String = "",
    val tags: List<String> = emptyList()
)

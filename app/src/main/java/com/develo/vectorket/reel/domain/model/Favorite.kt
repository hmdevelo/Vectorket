package com.develo.vectorket.reel.domain.model

import androidx.compose.runtime.Immutable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

@Immutable
data class Favorite(
    @DocumentId val uid: String = "",
    val userId: String = "",
    val vectorId: String = "",
    val createdAt: Timestamp = Timestamp.now()
)

package com.develo.vectorket.core.domain.model

import androidx.compose.runtime.Immutable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

@Immutable
data class Vector(
    @DocumentId val uid: String = "",
    val title: String = "",
    val createdAt: Timestamp = Timestamp.now(),
    val thumbnail: String = "",
    val imageUrl: String? = null,
    val images: List<String> = emptyList(),
    val bundled: Boolean = false,
    val premium: Boolean = false,
    val likeCount: Long = 0,
    val downloadCount: Long = 0,
    val fileInfo: FileInfo = FileInfo()
)

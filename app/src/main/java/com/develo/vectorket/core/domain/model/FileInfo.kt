package com.develo.vectorket.core.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class FileInfo(
    val width: Int = 0,
    val height: Int = 0,
    val size: Double = 0.0
)

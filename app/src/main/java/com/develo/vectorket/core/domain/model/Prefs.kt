package com.develo.vectorket.core.domain.model

data class Prefs(
    val searchHistories: List<String> = emptyList()
)
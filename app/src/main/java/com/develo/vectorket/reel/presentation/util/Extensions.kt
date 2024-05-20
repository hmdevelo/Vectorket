package com.develo.vectorket.reel.presentation.util

import com.develo.vectorket.reel.domain.model.Favorite

fun List<Favorite>.contains(vectorId: String) = this.find { it.vectorId == vectorId } != null
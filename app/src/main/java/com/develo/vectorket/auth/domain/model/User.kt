package com.develo.vectorket.auth.domain.model

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val photoUrl: String = ""
)
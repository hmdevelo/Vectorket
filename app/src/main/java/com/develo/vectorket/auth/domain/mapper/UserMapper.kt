package com.develo.vectorket.auth.domain.mapper

import com.develo.vectorket.auth.domain.model.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUser() = User(
    id = uid,
    name = displayName ?: "",
    email = email ?: "",
    photoUrl = photoUrl.toString()
)
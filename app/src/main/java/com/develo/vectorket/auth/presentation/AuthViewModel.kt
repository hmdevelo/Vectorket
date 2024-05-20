package com.develo.vectorket.auth.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develo.vectorket.auth.domain.alias.GoogleSignInResponse
import com.develo.vectorket.auth.domain.use_case.GetUserUseCase
import com.develo.vectorket.auth.domain.use_case.SignInWithGoogleUseCase
import com.develo.vectorket.core.domain.model.Response.Loading
import com.develo.vectorket.core.domain.model.Response.Success
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getUserUseCase: GetUserUseCase,
    val signInClient: GoogleSignInClient
) : ViewModel() {

    var googleSignInResponse by mutableStateOf<GoogleSignInResponse>(Success(false))
        private set

    val currentUser get() = getUserUseCase(viewModelScope)

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        googleSignInResponse = Loading
        googleSignInResponse = signInWithGoogleUseCase(googleCredential)
    }
}
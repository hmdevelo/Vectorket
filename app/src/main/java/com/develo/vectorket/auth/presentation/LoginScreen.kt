package com.develo.vectorket.auth.presentation

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.develo.vectorket.R
import com.develo.vectorket.auth.domain.alias.GoogleSignInResponse
import com.develo.vectorket.auth.presentation.component.GoogleSignIn
import com.develo.vectorket.auth.presentation.component.LoginHeader
import com.develo.vectorket.auth.presentation.component.LoginTopBar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    googleSignInResponse: GoogleSignInResponse,
    signInClient: GoogleSignInClient,
    signInWithGoogle: (AuthCredential) -> Unit,
    onSignIn: () -> Unit
) {
    val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val googleIdToken = task.getResult(ApiException::class.java)!!.idToken
                val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
                signInWithGoogle(googleCredential)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Scaffold(
        topBar = {
            LoginTopBar(title = LocalContext.current.getString(R.string.app_name))
        }
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddings.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var visible by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(Unit) {
                launch {
                    delay(300)
                    visible = true
                }
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(1000)) + slideInVertically(animationSpec = tween(1000))
            ) {
                LoginHeader(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(1000)) + slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(1000)
                )
            ) {
                GoogleSignIn(
                    googleSignInResponse = googleSignInResponse,
                    onSignIn = onSignIn,
                    onClick = {
                        launcher.launch(signInClient.signInIntent)
                    }
                )
            }
        }
    }
}

package com.develo.vectorket.auth.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develo.vectorket.R
import com.develo.vectorket.auth.domain.alias.GoogleSignInResponse
import com.develo.vectorket.core.domain.model.Response.Failure
import com.develo.vectorket.core.domain.model.Response.Loading
import com.develo.vectorket.core.domain.model.Response.Success
import com.develo.vectorket.core.presentation.LoadingPlaceholder

@Composable
fun GoogleSignIn(
    googleSignInResponse: GoogleSignInResponse,
    onSignIn: () -> Unit,
    onClick: () -> Unit
) {
    when (googleSignInResponse) {
        is Loading -> LoadingPlaceholder()

        is Success -> googleSignInResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                onSignIn()
            }
        }

        is Failure -> googleSignInResponse.e.printStackTrace()
    }

    if (googleSignInResponse != Loading) {
        GoogleSignInButton(onClick = onClick)
    }
}

@Composable
fun GoogleSignInButton(
    onClick: () -> Unit
) {
    OutlinedButton(onClick = onClick) {
        Text(
            text = "Continue with Google",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 8.dp)
        )
        Icon(
            painter = painterResource(R.drawable.ic_google_logo),
            contentDescription = "Google",
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun GoogleSignButtonPreview() {
    MaterialTheme {
        GoogleSignInButton(onClick = {})
    }
}
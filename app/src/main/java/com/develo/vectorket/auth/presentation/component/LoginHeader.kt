package com.develo.vectorket.auth.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develo.vectorket.R

@SuppressLint("ResourceType")
@Composable
fun LoginHeader(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.raw.login_cover_art),
            contentDescription = "Cover Art",
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun LoginHeaderPreview() {
    MaterialTheme {
        LoginHeader()
    }
}
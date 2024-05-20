package com.develo.vectorket.reel.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.develo.vectorket.core.domain.model.FileInfo
import com.develo.vectorket.core.presentation.ImageLoadingPlaceholder
import com.develo.vectorket.core.presentation.util.FORMAT_JPG
import com.develo.vectorket.core.presentation.util.Format_PNG
import com.develo.vectorket.core.presentation.util.aspectRatio
import com.develo.vectorket.core.presentation.util.fileFormat
import com.develo.vectorket.core.presentation.util.getDimensions

@Composable
fun ReelImage(
    modifier: Modifier = Modifier,
    url: String,
    fileInfo: FileInfo
) {
    val format = url.fileFormat
    val size = if (format == FORMAT_JPG) Size.ORIGINAL else fileInfo.getDimensions()

    Box(modifier) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .size(size)
                .build(),
            contentDescription = "Vector",
            contentScale = if (format == FORMAT_JPG) ContentScale.Crop else ContentScale.Fit,
            loading = { ImageLoadingPlaceholder() },
            modifier = Modifier
                .then(
                    if (format == Format_PNG) {
                        Modifier.aspectRatio(fileInfo.aspectRatio - 0.25f)
                    } else {
                        Modifier.fillMaxSize()
                    }
                )
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun ReelImagePreview() {
    MaterialTheme {
        ReelImage(
            url = "",
            fileInfo = FileInfo(
                width = 1024,
                height = 1024,
                size = 1048.0
            )
        )
    }
}
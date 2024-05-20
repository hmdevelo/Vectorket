package com.develo.vectorket.core.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.develo.vectorket.R

@Composable
fun WhatsHotIcon() {
    Icon(painter = painterResource(R.drawable.ic_whatshot), contentDescription = "What's hot")
}

@Composable
fun HistoryIcon() {
    Icon(painter = painterResource(R.drawable.ic_history), contentDescription = "History")
}

@Composable
fun ArrowOutwardIcon() {
    Icon(
        painter = painterResource(R.drawable.ic_arrow_outward),
        contentDescription = "Arrow Outward"
    )
}

@Composable
fun DownloadIcon() {
    Icon(
        painter = painterResource(R.drawable.ic_download),
        contentDescription = "Download"
    )
}

@Composable
fun BundleIcon(modifier: Modifier = Modifier, alpha: Float = 1f) {
    Icon(
        painter = painterResource(R.drawable.ic_bundle),
        contentDescription = "Bundle",
        modifier = modifier
            .graphicsLayer(alpha = alpha)
    )
}

@Composable
fun AspectRatioIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(R.drawable.ic_aspect_ratio),
        contentDescription = "Aspect Ratio",
        modifier = modifier
    )
}

@Composable
fun HomeIcon() {
    Icon(imageVector = Icons.Rounded.Home, contentDescription = "Home")
}

@Composable
fun SearchIcon() {
    Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
}

@Composable
fun BackIcon() {
    Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
}

@Composable
fun ArrowForwardIcon(
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    Icon(
        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
        contentDescription = "Forward",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun MoreVertIcon() {
    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More")
}

@Composable
fun ClearIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Rounded.Clear,
        contentDescription = "Clear",
        modifier = modifier
    )
}

@Composable
fun StarIcon(modifier: Modifier = Modifier) {
    val brush = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.tertiary,
            MaterialTheme.colorScheme.primary
        ),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription = "Star",
        tint = Color.Unspecified,
        modifier = modifier
            .graphicsLayer(alpha = 0.99f)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(brush = brush, blendMode = BlendMode.SrcAtop)
                }
            }
    )
}

@Composable
fun FavoriteBorderIcon(
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    Icon(
        imageVector = Icons.Rounded.FavoriteBorder,
        contentDescription = "Favorite Border",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun FavoriteIcon(
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    Icon(
        imageVector = Icons.Rounded.Favorite,
        contentDescription = "Favorite",
        tint = tint,
        modifier = modifier
    )
}
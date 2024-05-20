package com.develo.vectorket.home.presentation.component

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develo.vectorket.R
import com.develo.vectorket.core.domain.model.FileInfo
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.core.presentation.util.ArrowForwardIcon
import com.develo.vectorket.reel.presentation.component.ReelImage

@SuppressLint("ResourceType")
fun LazyListScope.heroBanner(
    modifier: Modifier = Modifier,
    state: LazyListState,
    latestVector: Vector,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    item {
        Box(modifier = modifier) {
            HeroSectionParallax(state)

            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
                visible = !isLoading,
                enter = fadeIn() + slideInVertically(
                    animationSpec = tween(1000),
                    initialOffsetY = { it })
            ) {
                LatestVector(
                    modifier = Modifier.width(300.dp),
                    vector = latestVector,
                    onClick = onClick
                )
            }

        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun HeroSectionParallax(
    state: LazyListState
) {
    val layer1TranslationY by remember {
        derivedStateOf {
            when {
                state.layoutInfo.visibleItemsInfo.isNotEmpty() &&
                        state.firstVisibleItemIndex == 0 ->
                    state.firstVisibleItemScrollOffset * 0.6f

                else -> 0f
            }
        }
    }

    val layer2TranslationY by remember {
        derivedStateOf {
            when {
                state.layoutInfo.visibleItemsInfo.isNotEmpty() &&
                        state.firstVisibleItemIndex == 0 ->
                    state.firstVisibleItemScrollOffset * 0.4f

                else -> 0f
            }
        }
    }


    Image(painter = painterResource(R.raw.hs_layer_1),
        contentDescription = "Hero Section",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer { translationY = layer1TranslationY }
    )
    Image(
        painter = painterResource(R.raw.hs_layer_2),
        contentDescription = "Hero Section",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer { translationY = layer2TranslationY }
    )
    Image(
        painter = painterResource(R.raw.hs_layer_3),
        contentDescription = "Hero Section",
        contentScale = ContentScale.Crop
    )
}

//@OptIn(UnstableApi::class)
//@Composable
//fun HeroAnimation(modifier: Modifier = Modifier) {
//    val context = LocalContext.current
//    val uri = Uri.parse("android.resource://${context.packageName}/${R.raw.demon}")
//    val mediaItem = MediaItem.fromUri(uri)
//    val exoPlayer = ExoPlayer.Builder(context).build().also {
//        it.repeatMode = Player.REPEAT_MODE_ONE
//        it.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
//        it.playWhenReady = true
//    }
//
//    LaunchedEffect(Unit) {
//        exoPlayer.setMediaItem(mediaItem)
//        exoPlayer.prepare()
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//    AndroidView(
//        factory = {
//            PlayerView(it).apply {
//                player = exoPlayer
//                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
//                isEnabled = false
//            }
//        },
//        modifier = modifier
//    )
//}

@Composable
fun LatestVector(
    modifier: Modifier = Modifier,
    vector: Vector,
    onClick: () -> Unit
) {
    val background = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f)

    Box(modifier = modifier
        .clip(MaterialTheme.shapes.large)
        .background(background)
        .clickable { onClick() }
        .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            vector.run {
                ReelImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(MaterialTheme.shapes.large)
                        .background(background),
                    url = thumbnail,
                    fileInfo = fileInfo
                )
            }

            Text(
                text = "Check out some of these latest images!",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        ArrowForwardIcon(Modifier.align(Alignment.CenterEnd))
    }
}

@Preview
@Composable
private fun LatestVectorPreview() {
    MaterialTheme {
        LatestVector(
            modifier = Modifier.width(320.dp),
            vector = Vector(fileInfo = FileInfo(800, 600)),
            onClick = {}
        )
    }
}
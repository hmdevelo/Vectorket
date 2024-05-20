package com.develo.vectorket.home.presentation.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develo.vectorket.core.domain.model.FileInfo
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.reel.presentation.component.ReelImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    items: List<Vector>,
    autoScrollDuration: Long = 7000L
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (isDragged.not()) {
        with(pagerState) {
            var currentPageKey by remember {
                mutableIntStateOf(0)
            }
            LaunchedEffect(currentPageKey) {
                launch {
                    delay(autoScrollDuration)
                    val nextPage = (currentPage + 1).mod(pageCount)
                    animateScrollToPage(
                        page = nextPage,
                        animationSpec = tween(durationMillis = 1000)
                    )
                    currentPageKey = nextPage
                }
            }
        }
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) { page ->
        CarouselItem(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            vector = items[page]
        )
    }
}

@Composable
fun CarouselItem(
    modifier: Modifier = Modifier,
    vector: Vector
) {
    vector.run {
        Box(
            modifier = modifier
        ) {
            ReelImage(url = thumbnail, fileInfo = fileInfo)
        }
    }
}

@Preview
@Composable
private fun CarouselItemPreview() {
    MaterialTheme {
        CarouselItem(
            modifier = Modifier
                .size(60.dp)
                .padding(4.dp),
            vector = Vector(
                title = "Cinderella drinking beer",
                premium = true,
                bundled = true,
                fileInfo = FileInfo(500, 500, 876.0)
            )
        )
    }
}
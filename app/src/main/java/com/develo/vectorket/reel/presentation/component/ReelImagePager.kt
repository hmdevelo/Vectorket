package com.develo.vectorket.reel.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.core.presentation.PagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelImagePager(
    modifier: Modifier = Modifier,
    vector: Vector
) {
    val state = rememberPagerState {
        vector.images.size
    }

    Box(modifier) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = state
        ) {
            ReelImage(
                modifier = Modifier.fillMaxSize(),
                url = vector.images[it],
                fileInfo = vector.fileInfo
            )
        }
        PagerIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 8.dp),
            state = state
        )
    }
}
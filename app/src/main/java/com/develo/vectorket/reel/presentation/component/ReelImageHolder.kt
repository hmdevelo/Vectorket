package com.develo.vectorket.reel.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.core.presentation.util.StarIcon

@Composable
fun ReelImageHolder(
    modifier: Modifier = Modifier,
    vector: Vector
) {
    Card(modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            vector.run {
                if (!bundled) {
                    ReelImage(
                        modifier = Modifier.fillMaxSize(),
                        url = imageUrl ?: "",
                        fileInfo = fileInfo
                    )
                } else {
                    ReelImagePager(
                        modifier = Modifier.fillMaxSize(),
                        vector = this
                    )
                }

                if (premium) {
                    StarIcon(Modifier.padding(8.dp))
                }
            }
        }
    }
}
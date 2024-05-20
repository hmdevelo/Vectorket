package com.develo.vectorket.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.Dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.size.Size
import com.develo.vectorket.core.domain.model.FileInfo
import com.develo.vectorket.core.domain.model.Response
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun Dp.toPx() = with(LocalDensity.current) { this@toPx.roundToPx() }

val <T : Any> LazyPagingItems<T>.isLoading
    get() = loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading

val <T>Response<T>.isLoading get() = this is Response.Loading
val <T>Response<T>.data get() = if (this is Response.Success) data else null

fun FileInfo.getDimensions(): Size {
    return if (width == 0) Size.ORIGINAL
    else {
        val reduceTimes = if (width <= 300) 1 else if (width <= 1024) 2 else 3
        Size(width / reduceTimes, height / reduceTimes)
    }
}

fun FileInfo.getSizeString() =
    if (size < 900) "${size.toNumberString()}KB" else "${(size / 1024).toNumberString()}MB"

val FileInfo.aspectRatio
    get() = if (width == 0 || height == 0) 1f
    else width.toFloat() / height.toFloat()

fun Long.formatCount() = if (this < 1000) this.toString()
else if (this < 1000000) (this.toDouble() / 1000).toNumberString() + "K"
else if (this < 1000000000) (this.toDouble() / 1000000).toNumberString() + "M"
else (this.toDouble() / 1000000000).toNumberString() + "B"

fun Double.toNumberString() = BigDecimal(this)
    .setScale(2, RoundingMode.HALF_EVEN)
    .toString()
    .dropLastWhile { it == '0' }
    .dropLastWhile { it == '.' }

val String.fileFormat
    get() = if (isBlank() || !contains(".")) Format_PNG
    else when (substring(lastIndexOf(".")).toLowerCase(Locale.current)) {
        FORMAT_JPEG -> FORMAT_JPG
        FORMAT_JPG -> FORMAT_JPG
        else -> Format_PNG
    }
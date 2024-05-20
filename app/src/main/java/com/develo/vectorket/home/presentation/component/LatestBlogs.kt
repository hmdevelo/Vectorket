package com.develo.vectorket.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.develo.vectorket.blog.domain.model.Blog
import com.develo.vectorket.core.presentation.BlogTag
import com.develo.vectorket.core.presentation.ImageLoadingPlaceholder
import com.develo.vectorket.core.presentation.LoadingPlaceholder

fun LazyListScope.latestBlogs(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    blogs: List<Blog>,
    onClick: (Int) -> Unit
) {
    if (isLoading) {
        item {
            LoadingPlaceholder(Modifier.padding(top = 16.dp))
        }
    } else {
        itemsIndexed(blogs) { index, blog ->
            key(blog.uid) {
                BlogListItem(
                    modifier = modifier,
                    blog = blog,
                    onClick = { onClick(index) }
                )
            }
        }
    }
}

@Composable
fun BlogListItem(
    modifier: Modifier = Modifier,
    blog: Blog,
    onClick: () -> Unit
) {
    Row(modifier = modifier
        .background(MaterialTheme.colorScheme.background)
        .clickable { onClick() }
        .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        blog.run {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Blog",
                contentScale = ContentScale.Crop,
                loading = { ImageLoadingPlaceholder() },
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Column {
                if (tags.isNotEmpty()) {
                    Row(
                        modifier = Modifier.padding(bottom = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        tags.forEach { BlogTag(text = it) }
                    }
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
private fun BlogListItemPreview() {
    MaterialTheme {
        BlogListItem(
            modifier = Modifier.fillMaxWidth(),
            blog = Blog(
                title = "This is test blog",
                description = "This is test description....",
                tags = listOf("Design", "How to")
            ),
            onClick = {})
    }
}
package com.develo.vectorket.profile.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.develo.vectorket.core.data.query.QueryBuilder
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.profile.data.util.getItems
import com.develo.vectorket.profile.data.util.sortWith
import com.develo.vectorket.reel.domain.model.Favorite
import com.google.firebase.firestore.FieldPath
import kotlinx.coroutines.tasks.await

class FavoritePagingSource(
    private val favorites: List<Favorite>,
    private val queryBuilder: QueryBuilder
) : PagingSource<Int, Vector>() {

    override fun getRefreshKey(state: PagingState<Int, Vector>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Vector> {
        return try {
            val page = params.key ?: 0
            val items = favorites.getItems(page, params.loadSize)
            val result = queryBuilder.build(
                FieldPath.documentId(),
                items.map { it.vectorId })
                .get()
                .await()
                .toObjects(Vector::class.java)
                .sortWith(items)

            LoadResult.Page(
                data = result,
                prevKey = if (page == 0) null else page.minus(1),
                nextKey = if (result.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
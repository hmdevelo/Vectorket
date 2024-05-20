package com.develo.vectorket.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.develo.vectorket.core.domain.model.Vector
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class VectorPagingSource(
    private val query: Query
) : PagingSource<QuerySnapshot, Vector>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, Vector>) = null

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Vector> {
        return try {
            val currentPage = params.key ?: query.get().await()
            val lastItem =
                if (currentPage.documents.isEmpty()) null else currentPage.documents.last()
            val nextPage = if (lastItem == null) null else query.startAfter(lastItem).get().await()
            LoadResult.Page(
                data = currentPage.toObjects(Vector::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
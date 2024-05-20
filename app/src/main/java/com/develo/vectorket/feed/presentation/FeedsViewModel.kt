package com.develo.vectorket.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.develo.vectorket.core.data.paging.VectorPagingSource
import com.develo.vectorket.core.domain.use_case.GetPagingSourceUseCase
import com.develo.vectorket.feed.domain.use_case.GetLatestVectorsQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val getPagingSource: GetPagingSourceUseCase,
    private val getLatestVectorsQuery: GetLatestVectorsQueryUseCase,
    config: PagingConfig
) : ViewModel() {

    private lateinit var pagingSource: VectorPagingSource

    val feeds = Pager(config) {
        val query = getLatestVectorsQuery(config.pageSize.toLong())
        getPagingSource(query).also { pagingSource = it }
    }.flow.cachedIn(viewModelScope)

    fun refresh() {
        pagingSource.invalidate()
    }
}
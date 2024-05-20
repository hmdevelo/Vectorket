package com.develo.vectorket.search.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.develo.vectorket.core.data.paging.VectorPagingSource
import com.develo.vectorket.core.domain.model.Prefs
import com.develo.vectorket.core.domain.use_case.GetPagingSourceUseCase
import com.develo.vectorket.core.domain.use_case.GetPrefsUseCase
import com.develo.vectorket.core.domain.use_case.SavePrefsUseCase
import com.develo.vectorket.core.domain.util.upsert
import com.develo.vectorket.search.domain.use_case.BuildQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getPagingSource: GetPagingSourceUseCase,
    private val buildQuery: BuildQueryUseCase,
    private val getPrefs: GetPrefsUseCase,
    private val savePrefs: SavePrefsUseCase,
    config: PagingConfig
) : ViewModel() {

    private val _criteria = mutableStateOf("")
    val criteria: State<String> = _criteria

    private lateinit var pagingSource: VectorPagingSource

    var prefs by mutableStateOf(Prefs())
        private set

    val result = Pager(config) {
        val query = buildQuery(_criteria.value)
        getPagingSource(query).also { pagingSource = it }
    }.flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            getPrefs().collect { prefs = it }
        }
    }

    fun search(criteria: String) {
        if (criteria.isNotBlank()) {
            _criteria.value = criteria.toLowerCase(Locale.current)
            pagingSource.invalidate()
            saveSearchHistory()
        }
    }

    private fun saveSearchHistory() {
        prefs = prefs.copy(
            searchHistories = prefs.searchHistories.upsert(_criteria.value)
        )
        viewModelScope.launch(Dispatchers.IO) {
            savePrefs(prefs)
        }
    }
}
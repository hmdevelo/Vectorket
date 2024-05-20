package com.develo.vectorket.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develo.vectorket.blog.domain.alias.BlogsResponse
import com.develo.vectorket.blog.domain.use_case.GetLatestBlogsUseCase
import com.develo.vectorket.core.domain.model.Response.Loading
import com.develo.vectorket.core.domain.model.Response.Success
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.home.domain.alias.LatestVectorResponse
import com.develo.vectorket.home.domain.use_case.GetLatestVectorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestVector: GetLatestVectorUseCase,
    private val getLatestBlogs: GetLatestBlogsUseCase
) : ViewModel() {

    var latestVectorResponse by mutableStateOf<LatestVectorResponse>(Success(Vector()))
        private set

    var latestBlogsResponse by mutableStateOf<BlogsResponse>(Success(emptyList()))

    init {
        refresh()
    }

    fun refresh() = viewModelScope.launch {
        latestVectorResponse = Loading
        latestBlogsResponse = Loading

        latestVectorResponse = getLatestVector()
        latestBlogsResponse = getLatestBlogs()
    }
}
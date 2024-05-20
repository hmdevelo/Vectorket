package com.develo.vectorket.profile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.develo.vectorket.profile.data.paging.FavoritePagingSource
import com.develo.vectorket.profile.domain.use_case.GetFavoritePagingSourceUseCase
import com.develo.vectorket.profile.domain.use_case.SignOutUseCase
import com.develo.vectorket.profile.presentation.ProfileEvent.*
import com.develo.vectorket.reel.domain.model.Favorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getPagingSource: GetFavoritePagingSourceUseCase,
    private val signOut: SignOutUseCase,
    config: PagingConfig
) : ViewModel() {

    private var _favorites by mutableStateOf(emptyList<Favorite>())
    private lateinit var pagingSource: FavoritePagingSource

    var favorites = Pager(config) {
        getPagingSource(_favorites).also { pagingSource = it }
    }.flow.cachedIn(viewModelScope)

    fun onEvent(event: ProfileEvent) {
        when (event) {
            LogOut -> viewModelScope.launch {
                signOut()
            }

            is ResetFavorites -> {
                try {
                    _favorites = event.favorites
                    pagingSource.invalidate()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}
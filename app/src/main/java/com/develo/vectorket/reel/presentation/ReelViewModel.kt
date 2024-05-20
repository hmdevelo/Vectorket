package com.develo.vectorket.reel.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develo.vectorket.auth.domain.use_case.GetUserUseCase
import com.develo.vectorket.reel.domain.mapper.toFavorite
import com.develo.vectorket.reel.domain.model.Favorite
import com.develo.vectorket.reel.domain.use_case.AddToFavoritesUseCase
import com.develo.vectorket.reel.domain.use_case.DownloadUseCase
import com.develo.vectorket.reel.domain.use_case.GetFavoritesUseCase
import com.develo.vectorket.reel.domain.use_case.RemoveFromFavoritesUseCase
import com.develo.vectorket.reel.domain.use_case.UpdateDownloadUseCase
import com.develo.vectorket.reel.presentation.ReelEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReelViewModel @Inject constructor(
    private val getCurrentUser: GetUserUseCase,
    private val getFavorites: GetFavoritesUseCase,
    private val addToFavorites: AddToFavoritesUseCase,
    private val removeFromFavorites: RemoveFromFavoritesUseCase,
    private val updateDownload: UpdateDownloadUseCase,
    private val download: DownloadUseCase
) : ViewModel() {

    val currentUser get() = getCurrentUser(viewModelScope).value

    var favorites by mutableStateOf(emptyList<Favorite>())
        private set

    init {
        viewModelScope.launch {
            currentUser?.let { user ->
                getFavorites(user.id).collectLatest {
                    favorites = it
                }
            }
        }
    }

    fun onEvent(event: ReelEvent) {
        when (event) {
            is AddToFavorites -> viewModelScope.launch {
                addToFavorites(event.vector.toFavorite(currentUser?.id!!))
            }

            is RemoveFromFavorites -> viewModelScope.launch {
                favorites.find { it.vectorId == event.vector.uid }?.let {
                    removeFromFavorites(it)
                }
            }

            is Download -> {
                event.vector.run {
                    if (!bundled) {
                        download(imageUrl ?: "", title)
                    } else {
                        images.forEachIndexed { index, url ->
                            download(url, "$title (${index + 1})")
                        }
                    }
                    viewModelScope.launch { updateDownload(this@run, 1) }
                }
            }
        }
    }
}
package com.develo.vectorket.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.develo.vectorket.auth.domain.model.User
import com.develo.vectorket.navigation.Screen.ProfileScreen
import com.develo.vectorket.navigation.Screen.ReelScreen
import com.develo.vectorket.profile.presentation.ProfileEvent.ResetFavorites
import com.develo.vectorket.profile.presentation.ProfileScreen
import com.develo.vectorket.profile.presentation.ProfileViewModel
import com.develo.vectorket.reel.presentation.ReelScreen
import com.develo.vectorket.reel.presentation.ReelViewModel

@Composable
fun ProfileNavHost() {
    val navController = rememberNavController()

    val reelViewModel = hiltViewModel<ReelViewModel>()
    val user = reelViewModel.currentUser ?: User()
    val favorites = reelViewModel.favorites

    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val favoriteVectors = profileViewModel.favorites.collectAsLazyPagingItems()

    var index by rememberSaveable {
        mutableIntStateOf(0)
    }

    val resetFavoriteVectors: () -> Unit = {
        profileViewModel.onEvent(ResetFavorites(favorites))
    }

    LaunchedEffect(favorites.size) {
        if (navController.currentDestination?.route == ProfileScreen.route) {
            resetFavoriteVectors()
        }
    }

    NavHost(
        navController = navController,
        startDestination = ProfileScreen.route
    ) {
        composable(ProfileScreen.route) {
            ProfileScreen(
                user = user,
                favorites = favoriteVectors,
                onFavoriteVectorClick = { i ->
                    index = i
                    navController.navigate(ReelScreen.route)
                },
                onEvent = { profileViewModel.onEvent(it) }
            )
        }
        composable(ReelScreen.route) {
            ReelScreen(
                items = favoriteVectors,
                index = index,
                favorites = favorites,
                title = "Favorites",
                onEvent = { reelViewModel.onEvent(it) },
                onIndexChange = {
                    index = it
                    resetFavoriteVectors()
                },
                onRefresh = { favoriteVectors.refresh() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
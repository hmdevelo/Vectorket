package com.develo.vectorket.main.presentation

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.develo.vectorket.auth.domain.model.User
import com.develo.vectorket.feed.presentation.FeedsViewModel
import com.develo.vectorket.main.presentation.component.BottomNavBar
import com.develo.vectorket.main.presentation.util.navigateTo
import com.develo.vectorket.navigation.navhost.HomeNavHost
import com.develo.vectorket.navigation.navhost.ProfileNavHost
import com.develo.vectorket.navigation.Screen.FeedsScreen
import com.develo.vectorket.navigation.Screen.HomeScreen
import com.develo.vectorket.navigation.Screen.ProfileScreen
import com.develo.vectorket.navigation.Screen.SearchScreen
import com.develo.vectorket.navigation.navhost.SearchNavHost
import com.develo.vectorket.reel.presentation.ReelScreen
import com.develo.vectorket.reel.presentation.ReelViewModel

@Composable
fun MainScreen(
    user: User
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                navController = navController,
                user = user
            )
        }
    ) { paddings ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddings.calculateStartPadding(LayoutDirection.Ltr),
                    end = paddings.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = paddings.calculateBottomPadding()
                ),
            navController = navController,
            startDestination = HomeScreen.route
        ) {
            composable(HomeScreen.route) {
                HomeNavHost(onNavigateToFeedsScreen = {
                    navController.navigateTo(FeedsScreen.route)
                })
            }
            composable(SearchScreen.route) {
                SearchNavHost()
            }
            composable(FeedsScreen.route) {
                val feedsViewModel = hiltViewModel<FeedsViewModel>()
                val feeds = feedsViewModel.feeds.collectAsLazyPagingItems()

                val reelViewModel = hiltViewModel<ReelViewModel>()
                val favorites = reelViewModel.favorites

                ReelScreen(
                    items = feeds,
                    index = 0,
                    title = "Feeds",
                    favorites = favorites,
                    showNavigateUp = false,
                    onEvent = { event -> reelViewModel.onEvent(event) },
                    onIndexChange = { },
                    onRefresh = { feedsViewModel.refresh() },
                    onNavigateUp = { navController.navigateUp() })
            }
            composable(ProfileScreen.route) {
                ProfileNavHost()
            }
        }
    }
}

package com.develo.vectorket.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.develo.vectorket.navigation.Screen.ReelScreen
import com.develo.vectorket.navigation.Screen.SearchScreen
import com.develo.vectorket.reel.presentation.ReelScreen
import com.develo.vectorket.reel.presentation.ReelViewModel
import com.develo.vectorket.search.presentation.SearchScreen
import com.develo.vectorket.search.presentation.SearchViewModel

@Composable
fun SearchNavHost() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<SearchViewModel>()
    val result = viewModel.result.collectAsLazyPagingItems()
    val searchHistories = viewModel.prefs.searchHistories
    val criteria = viewModel.criteria.value

    var index by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavHost(
        navController = navController,
        startDestination = SearchScreen.route
    ) {
        composable(SearchScreen.route) {
            SearchScreen(
                result = result,
                searchHistories = searchHistories,
                index = index,
                onSearch = { viewModel.search(it) },
                onClick = {
                    index = it
                    navController.navigate(ReelScreen.route)
                }
            )
        }
        composable(ReelScreen.route) {
            val reelViewModel = hiltViewModel<ReelViewModel>()
            val favorites = reelViewModel.favorites

            ReelScreen(
                items = result,
                index = index,
                title = criteria.ifBlank { "Explore" },
                favorites = favorites,
                onEvent = { reelViewModel.onEvent(it) },
                onIndexChange = { index = it },
                onRefresh = { result.refresh() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
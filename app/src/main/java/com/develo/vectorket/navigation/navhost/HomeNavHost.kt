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
import com.develo.vectorket.blog.presentation.BlogScreen
import com.develo.vectorket.core.presentation.util.data
import com.develo.vectorket.home.presentation.HomeScreen
import com.develo.vectorket.home.presentation.HomeViewModel
import com.develo.vectorket.navigation.Screen.BlogScreen
import com.develo.vectorket.navigation.Screen.HomeScreen

@Composable
fun HomeNavHost(
    onNavigateToFeedsScreen: () -> Unit
) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<HomeViewModel>()
    val latestItemsResponse = viewModel.latestVectorResponse
    val latestBlogsResponse = viewModel.latestBlogsResponse

    var blogIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavHost(
        navController = navController,
        startDestination = HomeScreen.route
    ) {
        composable(HomeScreen.route) {
            HomeScreen(
                latestVectorResponse = latestItemsResponse,
                latestBlogsResponse = latestBlogsResponse,
                onLatestVectorClick = onNavigateToFeedsScreen,
                onBlogClick = {
                    blogIndex = it
                    navController.navigate(BlogScreen.route)
                },
                onRefresh = viewModel::refresh
            )
        }
        composable(BlogScreen.route) {
            val blogs = latestBlogsResponse.data ?: emptyList()
            BlogScreen(
                blog = blogs[blogIndex],
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
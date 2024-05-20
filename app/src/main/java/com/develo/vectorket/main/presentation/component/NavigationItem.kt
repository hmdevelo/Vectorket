package com.develo.vectorket.main.presentation.component

import androidx.compose.runtime.Composable
import com.develo.vectorket.core.presentation.util.HomeIcon
import com.develo.vectorket.core.presentation.util.SearchIcon
import com.develo.vectorket.core.presentation.util.WhatsHotIcon
import com.develo.vectorket.navigation.Screen.FeedsScreen
import com.develo.vectorket.navigation.Screen.HomeScreen
import com.develo.vectorket.navigation.Screen.SearchScreen

data class NavigationItem(
    val label: String,
    val icon: @Composable () -> Unit,
    val route: String
) {
    companion object {
        val navigationItems = listOf(
            NavigationItem(
                label = "Home",
                icon = { HomeIcon() },
                route = HomeScreen.route
            ),
            NavigationItem(
                label = "Search",
                icon = { SearchIcon() },
                route = SearchScreen.route
            ),
            NavigationItem(
                label = "Feeds",
                icon = { WhatsHotIcon() },
                route = FeedsScreen.route
            )
        )
    }
}

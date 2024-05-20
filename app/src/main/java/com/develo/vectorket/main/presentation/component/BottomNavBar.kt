package com.develo.vectorket.main.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.develo.vectorket.auth.domain.model.User
import com.develo.vectorket.main.presentation.component.NavigationItem.Companion.navigationItems
import com.develo.vectorket.main.presentation.util.navigateTo
import com.develo.vectorket.navigation.Screen.ProfileScreen

@Composable
fun BottomNavBar(
    navController: NavHostController,
    user: User
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = item.route == currentRoute,
                onClick = {
                    navController.navigateTo(item.route)
                },
                icon = item.icon,
                label = { Text(text = item.label, style = MaterialTheme.typography.bodySmall) })
        }
        NavigationBarItem(
            selected = currentRoute == ProfileScreen.route,
            onClick = {
                navController.navigateTo(ProfileScreen.route)
            },
            icon = { UserAvatar(photoUrl = user.photoUrl) },
            label = { Text(text = "You", style = MaterialTheme.typography.bodySmall) })
    }
}
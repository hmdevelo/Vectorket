package com.develo.vectorket.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.develo.vectorket.auth.presentation.AuthViewModel
import com.develo.vectorket.auth.presentation.LoginScreen
import com.develo.vectorket.main.presentation.MainScreen
import com.develo.vectorket.navigation.Screen.*

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    val authViewModel = hiltViewModel<AuthViewModel>()
    val user = authViewModel.currentUser.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = if (user == null) LoginScreen.route else MainScreen.route
    ) {
        composable(LoginScreen.route) {
            LoginScreen(
                googleSignInResponse = authViewModel.googleSignInResponse,
                signInClient = authViewModel.signInClient,
                signInWithGoogle = { authViewModel.signInWithGoogle(it) },
                onSignIn = {
                    navController.navigate(MainScreen.route)
                    navController.popBackStack()
                }
            )
        }
        composable(MainScreen.route) {
            MainScreen(user = user!!)
        }
    }

    LaunchedEffect(user?.id) {
        if (user == null && navController.currentDestination?.route != LoginScreen.route) {
            navController.navigate(LoginScreen.route)
            navController.popBackStack()
        }
    }
}
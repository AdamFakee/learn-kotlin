package com.adamfakee.learnkotlin.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.adamfakee.learnkotlin.feature.home.NetworkDetailScreen
import com.adamfakee.learnkotlin.feature.home.NetworkScreen
import com.adamfakee.learnkotlin.feature.local.LocalScreen
import com.adamfakee.learnkotlin.navigation.LocalTab
import com.adamfakee.learnkotlin.navigation.NetWorkTab
import com.adamfakee.learnkotlin.navigation.NetworkDetailRoute
import com.adamfakee.learnkotlin.navigation.NetworkListRoute

@Composable
fun MainScreen() {
    val bottomNavController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigationBar(navController = bottomNavController) }) {
            innerPadding ->
        NavHost(
                navController = bottomNavController,
                startDestination = NetWorkTab,
                modifier = Modifier.padding(innerPadding)
        ) {
            navigation<NetWorkTab>(startDestination = NetworkListRoute) {
                composable<NetworkListRoute> {
                    NetworkScreen(
                            onPostClick = { postId ->
                                bottomNavController.navigate(NetworkDetailRoute(postId))
                            }
                    )
                }
                composable<NetworkDetailRoute> {
                    NetworkDetailScreen(onBackClick = { bottomNavController.popBackStack() })
                }
            }
            composable<LocalTab> { LocalScreen() }
        }
    }
}

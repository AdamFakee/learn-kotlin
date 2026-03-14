package com.adamfakee.learnkotlin.feature.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.adamfakee.learnkotlin.navigation.LocalTab
import com.adamfakee.learnkotlin.navigation.NetWorkTab

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Network") },
            label = { Text("Network") },
            selected = currentDestination?.hierarchy?.any { it.hasRoute<NetWorkTab>() } == true,
            onClick = {
                navController.navigate(NetWorkTab) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Local") },
            label = { Text("Local") },
            selected = currentDestination?.hierarchy?.any { it.hasRoute<LocalTab>() } == true,
            onClick = {
                navController.navigate(LocalTab) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
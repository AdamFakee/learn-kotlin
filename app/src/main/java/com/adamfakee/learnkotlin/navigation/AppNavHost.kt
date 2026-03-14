package com.adamfakee.learnkotlin.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.adamfakee.learnkotlin.feature.main.MainScreen
import com.adamfakee.learnkotlin.feature.onboarding.OnboardingScreen
import com.adamfakee.learnkotlin.feature.onboarding.OnboardingViewModel

@Composable
fun AppNavHost(isOnboardingCompleted: Boolean) {
    val rootNavController = rememberNavController()
    val startGraph = if (isOnboardingCompleted) MainGraph else OnboardingGraph

    NavHost(navController = rootNavController, startDestination = startGraph) {
        // --- LUỒNG 1: ONBOARDING ---
        navigation<OnboardingGraph>(startDestination = OnboardingRoute) {
            composable<OnboardingRoute> {
                // Khởi tạo ViewModel cho Onboarding
                val onboardingViewModel: OnboardingViewModel = hiltViewModel()

                OnboardingScreen(
                        viewModel = onboardingViewModel,
                        onFinishOnboarding = {
                            // Nhảy sang Main và xóa sạch dấu vết Onboarding
                            rootNavController.navigate(MainGraph) {
                                popUpTo<OnboardingGraph> { inclusive = true }
                            }
                        }
                )
            }
        }

        // --- LUỒNG 2: MAIN (Có TabBar) ---
        composable<MainGraph> { MainScreen() }
    }
}

package com.adamfakee.learnkotlin.feature.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel, onFinishOnboarding: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Chào mừng bạn đến với App!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Lưu DataStore xong mới gọi callback chuyển màn hình
            viewModel.completeOnboarding(onFinished = onFinishOnboarding)
        }) {
            Text("Bắt đầu trải nghiệm")
        }
    }
}
package com.adamfakee.learnkotlin.navigation

import kotlinx.serialization.Serializable

// --- Các Graph chính ---
@Serializable data object OnboardingGraph

@Serializable data object MainGraph

// --- Các màn hình trong Onboarding ---
@Serializable
data object OnboardingRoute // Màn hình giới thiệu

// --- Các Tab trong luồng Main ---
@Serializable data object NetWorkTab

@Serializable
data object NetworkListRoute // Màn hình danh sách chính của tab Network

@Serializable data object LocalTab

// --- Màn hình con ---
@Serializable data class NetworkDetailRoute(val id: Int)

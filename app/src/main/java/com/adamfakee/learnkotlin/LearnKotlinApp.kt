package com.adamfakee.learnkotlin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Khởi tạo DI Hilt để sử dụng trong toàn ứng dụng, dùng các biến như context...
@HiltAndroidApp class LearnKotlinApp : Application()

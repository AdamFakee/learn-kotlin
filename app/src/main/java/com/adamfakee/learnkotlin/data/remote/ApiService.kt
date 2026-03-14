package com.adamfakee.learnkotlin.data.remote

import retrofit2.http.GET

interface ApiService {

    // Ví dụ mẫu: Lấy ra danh sách data từ API
    @GET("example/endpoint") suspend fun getData(): Any
}

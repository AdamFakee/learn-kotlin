package com.adamfakee.learnkotlin.feature.home.data

import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path

@Serializable data class Post(val id: Int, val userId: Int, val title: String, val body: String)

@Serializable
data class Comment(
        val id: Int,
        val postId: Int,
        val name: String,
        val email: String,
        val body: String
)

interface SocialApiService {
    @GET("posts") suspend fun getPosts(): List<Post>

    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") postId: Int): List<Comment>
}

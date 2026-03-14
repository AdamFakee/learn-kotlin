package com.adamfakee.learnkotlin.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamfakee.learnkotlin.feature.home.data.Post
import com.adamfakee.learnkotlin.feature.home.data.SocialApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NetworkViewModel @Inject constructor(private val socialApiService: SocialApiService) :
        ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Gọi API lấy danh sách bài viết
                val response = socialApiService.getPosts()
                _posts.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                // Xử lý lỗi ở đây
            } finally {
                _isLoading.value = false
            }
        }
    }
}

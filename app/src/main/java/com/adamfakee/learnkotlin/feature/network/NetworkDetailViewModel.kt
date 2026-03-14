package com.adamfakee.learnkotlin.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.adamfakee.learnkotlin.feature.home.data.Comment
import com.adamfakee.learnkotlin.feature.home.data.SocialApiService
import com.adamfakee.learnkotlin.navigation.NetworkDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NetworkDetailViewModel
@Inject
constructor(private val socialApiService: SocialApiService, savedStateHandle: SavedStateHandle) :
        ViewModel() {

    private val route = savedStateHandle.toRoute<NetworkDetailRoute>()
    private val postId: Int = route.id

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadComments()
    }

    private fun loadComments() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Gọi API lấy bình luận của bài viết được nhấp
                val response = socialApiService.getComments(postId)
                _comments.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}

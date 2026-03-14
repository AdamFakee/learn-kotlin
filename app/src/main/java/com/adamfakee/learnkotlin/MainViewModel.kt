package com.adamfakee.learnkotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamfakee.learnkotlin.data.local.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

// tự khởi tạo DataStoreManager thông qua Hilt DI
@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
        ViewModel() {

    // StateFlow, giá trị ban đầu là null (đang loading)
    val isOnboardingCompleted: StateFlow<Boolean?> =
            dataStoreManager
                    .getPreferenceFlow(DataStoreManager.ONBOARDING_COMPLETED_KEY, false)
                    .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(5000),
                            initialValue = null
                    )
}

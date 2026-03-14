package com.adamfakee.learnkotlin.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamfakee.learnkotlin.data.local.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
        ViewModel() {

    fun completeOnboarding(onFinished: () -> Unit) {
        viewModelScope.launch {
            dataStoreManager.savePreference(DataStoreManager.ONBOARDING_COMPLETED_KEY, true)
            onFinished()
        }
    }
}

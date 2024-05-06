package com.yemyatthu.usercentricsappchallenge.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsConsentUserResponse
import com.yemyatthu.usercentricsappchallenge.domain.usecase.CalculateCostsUseCase
import com.yemyatthu.usercentricsappchallenge.domain.usecase.GetConsentedServicesUseCase
import com.yemyatthu.usercentricsappchallenge.presentation.converter.UsercentricsConsentConsentConverter
import com.yemyatthu.usercentricsappchallenge.presentation.converter.UsercentricsConsentInteractionConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val calculateCostsUseCase: CalculateCostsUseCase,
    private val getConsentedServices: GetConsentedServicesUseCase,
    private val usercentricsConsentConsentConverter: UsercentricsConsentConsentConverter,
    private val usercentricsConsentInteractionConverter: UsercentricsConsentInteractionConverter
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Initial)
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()
    init {
        viewModelScope.launch {
            Usercentrics.isReady(onSuccess = {
                //Only enable show consent banner button when the sdk is ready
                _uiState.value = HomeScreenUiState.EnabledConsentButton(true)
            }, onFailure =  {
                _uiState.value = HomeScreenUiState.EnabledConsentButton(false)
            })
        }
    }
    fun onConsentButtonClicked() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUiState.ShowConsentBanner
        }
    }

    fun onConsentResult(response: UsercentricsConsentUserResponse?) {
        viewModelScope.launch {
            if (response != null) {
                val consents =  usercentricsConsentConsentConverter(response.consents)
                val interaction = usercentricsConsentInteractionConverter(response.userInteraction)
                val consentedServices = getConsentedServices(interaction,consents)
                val costs = calculateCostsUseCase(consentedServices)
                for ((key,value) in costs.serviceCosts) {
                    println("$key = $value")
                }
                println("Total = ${costs.totalCost}")
                _uiState.value = HomeScreenUiState.ShowCost(costs.totalCost)
            } else {
                _uiState.value = HomeScreenUiState.Error("Error fetching consented services")
            }
        }
    }
}
package com.yemyatthu.usercentricsappchallenge.presentation.ui.screens.home

sealed class HomeScreenUiState {
    data object Initial : HomeScreenUiState()
    data object ShowConsentBanner : HomeScreenUiState()
    data class ShowCost(val totalCost: Int) : HomeScreenUiState()
    data class Error(val message: String) : HomeScreenUiState()
}
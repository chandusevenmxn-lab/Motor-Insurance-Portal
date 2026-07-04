package com.example.motor_insurance_portal.ui.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motor_insurance_portal.data.remote.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val apiService: ExperienceApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow<QuoteUiState>(QuoteUiState.Idle)
    val uiState: StateFlow<QuoteUiState> = _uiState

    private val _quoteSummary = MutableStateFlow<QuoteSummaryResponse?>(null)
    val quoteSummary: StateFlow<QuoteSummaryResponse?> = _quoteSummary

    fun submitVehicleDetails(
        registration: String,
        make: String,
        model: String,
        year: String,
        value: String
    ) {
        viewModelScope.launch {
            _uiState.value = QuoteUiState.Loading
            try {
                val request = VehicleDetailsRequest(
                    registrationNumber = registration,
                    make = make,
                    model = model,
                    year = year.toIntOrNull() ?: 0,
                    vehicleValue = value.toDoubleOrNull() ?: 0.0
                )
                val response = apiService.submitVehicleDetails(request)
                // In a real app, we'd navigate and fetch the summary
                fetchQuoteSummary(response.quoteId)
            } catch (e: Exception) {
                _uiState.value = QuoteUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    private fun fetchQuoteSummary(quoteId: String) {
        viewModelScope.launch {
            try {
                val summary = apiService.calculatePremium(quoteId)
                _quoteSummary.value = summary
                _uiState.value = QuoteUiState.Success("Quote generated: ${summary.totalPremium}")
            } catch (e: Exception) {
                _uiState.value = QuoteUiState.Error(e.message ?: "Failed to fetch summary")
            }
        }
    }

    fun issuePolicy(quoteId: String) {
        viewModelScope.launch {
            _uiState.value = QuoteUiState.Loading
            try {
                val request = PolicyIssuanceRequest(
                    quoteId = quoteId,
                    paymentMethod = "CREDIT_CARD",
                    confirmationAccepted = true
                )
                val response = apiService.issuePolicy(request)
                _uiState.value = QuoteUiState.Success("Policy Issued: ${response.policyNumber}")
            } catch (e: Exception) {
                _uiState.value = QuoteUiState.Error(e.message ?: "Issuance failed")
            }
        }
    }
}

sealed class QuoteUiState {
    object Idle : QuoteUiState()
    object Loading : QuoteUiState()
    data class Success(val message: String) : QuoteUiState()
    data class Error(val message: String) : QuoteUiState()
}

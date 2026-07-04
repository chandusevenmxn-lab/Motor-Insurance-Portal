package com.example.motor_insurance_portal.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class VehicleDetailsRequest(
    val registrationNumber: String,
    val make: String,
    val model: String,
    val year: Int,
    val vehicleValue: Double
)

@Serializable
data class QuoteResponse(
    val quoteId: String,
    val referenceNumber: String,
    val status: String
)

@Serializable
data class QuoteSummaryResponse(
    val quoteId: String,
    val basePremium: Double,
    val taxes: Double,
    val totalPremium: Double,
    val currency: String = "USD"
)

@Serializable
data class PolicyIssuanceRequest(
    val quoteId: String,
    val paymentMethod: String,
    val confirmationAccepted: Boolean
)

@Serializable
data class PolicyResponse(
    val policyNumber: String,
    val status: String,
    val effectiveDate: String
)

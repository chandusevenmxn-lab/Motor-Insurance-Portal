package com.example.motor_insurance_portal.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ExperienceApiService {
    
    // JNY-QT-001 Step 1: Vehicle Details -> Experience Layer
    @POST("motor/v1/quotes")
    suspend fun submitVehicleDetails(@Body request: VehicleDetailsRequest): QuoteResponse

    // JNY-QT-001 Step 2: Quote Summary -> Process Layer
    @POST("quotation/v1/motor/quotes")
    suspend fun calculatePremium(@Body quoteId: String): QuoteSummaryResponse

    // JNY-ISS-001 Step 1: Issuance -> Process Layer
    @POST("issuance/v1/motor/policies")
    suspend fun issuePolicy(@Body request: PolicyIssuanceRequest): PolicyResponse

    @GET("experience/customer")
    suspend fun getCustomer(): CustomerResponse

    @GET("experience/dashboard")
    suspend fun getDashboardData(): DashboardResponse
}

// Retaining existing aggregate models if needed, though QuoteResponse is now in QuoteModels.kt
data class CustomerResponse(val name: String, val id: String)
data class DashboardResponse(val activePolicies: Int, val pendingQuotes: Int)

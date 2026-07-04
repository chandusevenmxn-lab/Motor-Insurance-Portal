package com.example.motor_insurance_portal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.motor_insurance_portal.ui.individual.IndividualPortalScreen
import com.example.motor_insurance_portal.ui.fleet.FleetPortalScreen
import com.example.motor_insurance_portal.ui.login.LoginScreen
import com.example.motor_insurance_portal.ui.quote.QuoteSummaryScreen
import com.example.motor_insurance_portal.ui.quote.QuoteViewModel
import com.example.motor_insurance_portal.ui.quote.VehicleDetailsScreen
import com.example.motor_insurance_portal.ui.theme.MotorInsurancePortalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotorInsurancePortalTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val quoteViewModel: QuoteViewModel = hiltViewModel()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(onLoginSuccess = {
                    navController.navigate("individual") {
                        popUpTo("login") { inclusive = true }
                    }
                })
            }
            composable("individual") {
                IndividualPortalScreen(onStartQuote = {
                    navController.navigate("vehicle_details")
                })
            }
            composable("vehicle_details") {
                VehicleDetailsScreen(
                    viewModel = quoteViewModel,
                    onNext = { navController.navigate("quote_summary") }
                )
            }
            composable("quote_summary") {
                QuoteSummaryScreen(
                    viewModel = quoteViewModel,
                    onIssueSuccess = {
                        navController.navigate("individual") {
                            popUpTo("individual") { inclusive = true }
                        }
                    }
                )
            }
            composable("fleet") {
                FleetPortalScreen()
            }
        }
    }
}

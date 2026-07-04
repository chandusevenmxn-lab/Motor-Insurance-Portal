package com.example.motor_insurance_portal.ui.quote

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuoteSummaryScreen(
    viewModel: QuoteViewModel,
    onIssueSuccess: () -> Unit
) {
    val summary by viewModel.quoteSummary.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("MOTOR-QT-002: Quote Summary", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        summary?.let {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Premium Calculation", style = MaterialTheme.typography.titleMedium)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text("Base Premium:")
                        Text("${it.currency} ${it.basePremium}")
                    }
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text("Taxes:")
                        Text("${it.currency} ${it.taxes}")
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text("Total Premium:", style = MaterialTheme.typography.titleLarge)
                        Text("${it.currency} ${it.totalPremium}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (uiState is QuoteUiState.Loading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = { viewModel.issuePolicy(it.quoteId) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirm Proposal & Buy Policy")
                }
            }
        } ?: Text("No quote data found.")

        if (uiState is QuoteUiState.Success && (uiState as QuoteUiState.Success).message.contains("Policy Issued")) {
            Text((uiState as QuoteUiState.Success).message, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 16.dp))
            Button(onClick = onIssueSuccess, modifier = Modifier.padding(top = 8.dp)) {
                Text("Return to Dashboard")
            }
        }

        if (uiState is QuoteUiState.Error) {
            Text((uiState as QuoteUiState.Error).message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

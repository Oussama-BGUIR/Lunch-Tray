package com.example.lunchtray.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchtray.R

@Composable
fun StartOrderScreen(
    onStartOrderButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onStartOrderButtonClicked,
            modifier = Modifier.widthIn(min = 250.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976D2), // Blue
                contentColor = Color.White
            )
        ) {
            Text(text = "Démarrer la commande") // French label
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartOrderScreenPreview() {
    StartOrderScreen(
        onStartOrderButtonClicked = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}

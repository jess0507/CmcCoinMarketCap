package com.jess.coinmarketcapapiapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun Loading() {
    Box (contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}
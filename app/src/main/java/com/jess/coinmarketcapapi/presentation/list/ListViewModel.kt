package com.jess.coinmarketcapapi.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.coinmarketcapapi.data.remote.CryptoRepository
import com.jess.coinmarketcapapi.data.remote.Resource
import com.jess.coinmarketcapapi.data.remote.dto.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: CryptoRepository) :
    ViewModel() {
    var state by mutableStateOf<List<Currency>>(emptyList())

    init {
        fetchLatestList()
    }

    private fun fetchLatestList() {
        viewModelScope.launch {
            repository.fetchLatestList().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = it
                        }
                    }
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                }
            }
        }
    }
}
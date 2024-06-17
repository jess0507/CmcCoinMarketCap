package com.jess.coinmarketcapapiapp.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.coinmarketcapapiapp.data.remote.Resource
import com.jess.coinmarketcapapiapp.data.remote.dto.Currency
import com.jess.coinmarketcapapiapp.data.repository.CryptoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: CryptoRepositoryImpl) :
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
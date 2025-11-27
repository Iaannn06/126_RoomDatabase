package com.example.teoriweek9.viewmodel

import androidx.lifecycle.ViewModel
import com.example.teoriweek9.Repositori.RepositoriSiswa

class HomeViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


val homeUIState: StateFLow<HomeUiState> = repositoriSiswa.getAllSiswaStream()
    .filterNotNUll()
    .map {HomeUiState(listSiswa = it.toList())}


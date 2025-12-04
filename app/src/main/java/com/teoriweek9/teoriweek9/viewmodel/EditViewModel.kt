package com.teoriweek9.teoriweek9.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teoriweek9.view.route.DestinasiDetailSiswa
import com.example.teoriweek9.viewmodel.DetailSiswa
import com.example.teoriweek9.viewmodel.UIStateSiswa
import com.example.teoriweek9.viewmodel.toUiStateSiswa
import com.teoriweek9.room.RepositoriSiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.teoriweek9.viewmodel.toSiswa

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateSiswa = repositoriSiswa.getSiswaStream(idSiswa)
                .filterNotNull()
                .first()
                .toUiStateSiswa(true)
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput(uiStateSiswa.detailSiswa)) {
            repositoriSiswa.updateSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}
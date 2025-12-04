package com.example.teoriweek9.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teoriweek9.viewmodel.DetailSiswa
import com.example.teoriweek9.viewmodel.toDetailSiswa
import com.example.teoriweek9.viewmodel.toSiswa
import com.example.teoriweek9.view.route.DestinasiDetailSiswa
import com.teoriweek9.room.RepositoriSiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel(){

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdArg])

    val uiDetailState: StateFlow<DetailSiswaUiState> =
        repositoriSiswa.getSiswaStream(idSiswa)
            .filterNotNull()
            .map {
                DetailSiswaUiState(detailSiswa = it.toDetailSiswa())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailSiswaUiState()
            )
    suspend fun deleteSiswa(){
        repositoriSiswa.deleteSiswa(uiDetailState.value.detailSiswa.toSiswa())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for ItemDetailsScreen
 */
data class DetailSiswaUiState(
    val detailSiswa: DetailSiswa = DetailSiswa()
)

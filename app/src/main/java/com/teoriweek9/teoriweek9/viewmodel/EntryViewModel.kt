package com.example.myroomsatu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroomsatu.repositori.RepositoriSiswa
import com.example.myroomsatu.room.Siswa
import com.teoriweek9.teoriweek9.Repositori.RepositoriSiswa
import com.teoriweek9.teoriweek9.room.Siswa
import kotlinx.coroutines.launch

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel() {

    // Berisi status Siswa saat ini (StateFlow tidak diperlukan jika state hanya di-update dari ViewModel)
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set // Hanya bisa diubah di dalam ViewModel

    /**
     * Fungsi untuk memvalidasi input
     */
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    /**
     * Memperbarui UI State dan status validasi setiap kali input berubah
     */
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    /**
     * Fungsi untuk menyimpan data yang di-entry
     */
    suspend fun saveSiswa() {
        // Melakukan validasi terakhir sebelum menyimpan
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}

// =========================================================================

/**
 * Data Class yang merepresentasikan status UI secara keseluruhan
 */
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

/**
 * Data Class yang menyimpan detail siswa sementara saat user input
 */
data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

/**
 * Fungsi ekstensi untuk mengkonversi DetailSiswa (input) ke Siswa (tabel database)
 */
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

/**
 * Fungsi ekstensi untuk mengkonversi Siswa (tabel database) ke DetailSiswa (UI state)
 */
fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

/**
 * Fungsi ekstensi (belum digunakan, tetapi ada di gambar)
 */
fun UIStateSiswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)
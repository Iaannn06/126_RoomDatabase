package com.teoriweek9.teoriweek9.viewmodel.provider


import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tu.repositori.AplikasiSiswa // Disesuaikan
import com.example.myroomsatu.viewmodel.EntryViewModel
import com.example.myroomsatu.viewmodel.HomeViewModel

/**
 * Objek yang menyediakan instance [ViewModelFactory] untuk setiap Layar (Screen) Compose.
 */
object PenyediaViewModel {

    /**
     * Objek [ViewModelFactory] untuk membuat instance [HomeViewModel] dan [EntryViewModel]
     * dan meneruskan dependensi yang diperlukan.
     */
    val Factory = viewModelFactory {
        // Inisialisasi HomeViewModel
        initializer {
            // Mengambil RepositoriSiswa dari container AplikasiSiswa
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        // Inisialisasi EntryViewModel
        initializer {
            // Mengambil RepositoriSiswa dari container AplikasiSiswa
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }
    }
}

/**
 * Fungsi ekstensi [CreationExtras] untuk mengambil objek [Application] dan
 * mengembalikan instance dari [AplikasiSiswa].
 */
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application).let {
        it as AplikasiSiswa
    }
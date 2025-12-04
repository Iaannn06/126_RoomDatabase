package com.teoriweek9.teoriweek9.viewmodel.provider


import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myroomsatu.viewmodel.EntryViewModel
import com.example.teoriweek9.viewmodel.DetailViewModel
import com.teoriweek9.teoriweek9.Repositori.AplikasiSiswa
import com.teoriweek9.teoriweek9.viewmodel.HomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            DetailViewModel(this.createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa)
        }

        //initializer {
        //    EditViewModel(this.createSavedStateHandle(),aplikasiSiswa(),container.repositoriSiswa)
        //}
    }
}

/**

Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah
instance dari [AplikasiSiswa].*/
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)
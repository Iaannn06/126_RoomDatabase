package com.teoriweek9.teoriweek9.viewmodel.provider


import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.teoriweek9.viewmodel.DetailViewModel
import com.example.teoriweek9.viewmodel.EntryViewModel
import com.teoriweek9.teoriweek9.Repositori.AplikasiSiswa
import com.teoriweek9.teoriweek9.view.route.HomeViewModel
import com.teoriweek9.teoriweek9.viewmodel.EditViewModel


object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            DetailViewModel(this.createSavedStateHandle(), aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            EditViewModel(this.createSavedStateHandle(), aplikasiSiswa().container.repositoriSiswa)
        }
    }
}


fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)
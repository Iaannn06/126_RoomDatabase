package com.example.teoriweek9.Repositori

import android.content.Context
import com.example.teoriweek9.room.DatabaseSiswa

interface ContainerApp {
    val repositoriSiswa : RepositoriSiswa
}

class ContainerDataAPp(private val context: Context):
        ContainerApp{
            override val repositoriSiswa: RepositoriSiswa by lazy {
                OfflineRepositoriSiswa(
                siswaDao = DatabaseSiswa.getDatabase(context).siswaDao())
            }
        }
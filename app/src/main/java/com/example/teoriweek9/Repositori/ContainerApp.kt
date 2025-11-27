package com.example.teoriweek9.Repositori

import android.content.Context

interface ContainerApp {
    val repositoriSiswa : RepositoriSiswa
}

class ContainerDataAPp(private val context: Context):
        ContainerApp{
            override val repositoriSiswa: RepositoriSiswa by lazy {

            }
        }
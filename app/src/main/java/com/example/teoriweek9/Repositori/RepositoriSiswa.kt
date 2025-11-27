package com.example.teoriweek9.Repositori

import com.example.teoriweek9.room.Siswa
import kotlinx.coroutines.flow.Flow

interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>

}
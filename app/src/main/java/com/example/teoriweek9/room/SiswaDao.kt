package com.example.teoriweek9.room

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface SiswaDao {
    @Query(value ="SELECT * from tblSiswa ORDER BY nama ASC")
    fun getAllSiswa(): Flow<List<Siswa>>


}
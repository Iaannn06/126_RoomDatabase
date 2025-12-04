package com.teoriweek9.teoriweek9.room



import androidx.room.Insert

import androidx.room.OnConflictStrategy

import androidx.room.Query

import kotlinx.coroutines.flow.Flow


interface RepositoriSiswa{
    fun getAllSiswaStream(): Flow<List<Siswa>>
    suspend fun insertSiswa(siswa: Siswa)
    fun getSiswaStream(id: Int): Flow<Siswa?>
    suspend fun deleteSiswa(siswa: Siswa)
}
class OfflineRepositoriSiswa(
    private val siswaDao: SiswaDao
): RepositoriSiswa {
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()
    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)
    override fun getSiswaStream(id: Int): Flow<Siswa?> = siswaDao.getSiswa(id)
    override suspend fun deleteSiswa(siswa: Siswa) = siswaDao.delete(siswa)
}
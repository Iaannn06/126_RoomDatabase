package com.example.teoriweek9.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Siswa::class], version = 1, exportSchema = false)
abstract class DatabaseSiswa : RoomDatabase(){
    abstract fun siswaDao() : SiswaDao
}
package com.example.teoriweek9.room

import androidx.room.Query

interface SiswaDao {
    @Query(value ="SELECT * from tblSiswa ORDER BY nama ASC")
}
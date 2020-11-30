package com.ananda.oop2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mahasiswa")
data class Mahasiswa(
    @PrimaryKey val nim: Int,
    @ColumnInfo(name = "nama") val nama: String?,
    @ColumnInfo(name = "prodi") val prodi: String?
)
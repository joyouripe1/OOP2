package com.ananda.oop2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Playstation")
data class Playstation(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "durasi") val durasi: String?,
    @ColumnInfo(name = "jenis_jaminan") val jenis_jaminan: String?
)
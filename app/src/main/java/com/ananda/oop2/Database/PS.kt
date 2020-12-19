package com.ananda.oop2.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ps")
data class PS(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "durasi") val durasi: String,
    @ColumnInfo(name = "jenis_jaminan") val jenis_jaminan: String
)
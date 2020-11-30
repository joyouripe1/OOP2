package com.ananda.oop2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MahasiswaDao {
    @Query("SELECT * FROM mahasiswa")
    fun getAll(): List<Mahasiswa>

    @Query("SELECT * FROM mahasiswa WHERE nim IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Mahasiswa>

    @Query("SELECT * FROM mahasiswa WHERE nama LIKE :nama AND " +
            "prodi LIKE :prodi LIMIT 1")
    fun findByName(nama: String, prodi: String): Mahasiswa

    @Insert
    fun insertAll(vararg mahasiswa: Mahasiswa)

    @Delete
    fun delete(mahasiswa: Mahasiswa)
}
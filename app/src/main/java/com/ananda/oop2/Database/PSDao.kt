package com.ananda.oop2.Database

import androidx.room.*
import com.ananda.oop2.Database.PS

@Dao
interface PSDao {
    @Insert
    suspend fun addPS(ps: PS)

    @Update
    suspend fun updatePS(ps: PS)

    @Delete
    suspend fun deletePS(ps: PS)

    @Query("SELECT * FROM PS")
    suspend fun getAllPS(): List<PS>

    @Query("SELECT * FROM PS WHERE id=:ps_id")
    suspend fun getPs(ps_id: Int) : List<PS>
}
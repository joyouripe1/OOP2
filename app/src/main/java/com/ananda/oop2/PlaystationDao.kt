package com.ananda.oop2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import  androidx.room.Insert
import  androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ananda.oop2.Playstation


@Dao
interface PlaystationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  addPlaystation(playstation: Playstation)

    @Query (value = "SELECT * FROM Playstation ORDER BY id ASC")
    fun readAllData(): LiveData<List<Playstation>>
}
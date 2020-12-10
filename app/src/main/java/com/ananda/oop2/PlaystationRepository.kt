package com.ananda.oop2

import androidx.lifecycle.LiveData

class PlaystationRepository (private val playstationDao:PlaystationDao){

    val readAllData: LiveData<List<Playstation>> = playstationDao.readAllData()

    suspend fun addPlaystation (playstation: Playstation){
        playstationDao.addPlaystation(playstation)
    }
}
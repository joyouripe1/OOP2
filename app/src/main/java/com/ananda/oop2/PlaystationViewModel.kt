package com.ananda.oop2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlaystationViewModel(aplication: Application): AndroidViewModel(aplication){
    private val readAllData: LiveData<List<Playstation>>
    private val repository: PlaystationRepository

    init {
        val playstationDao = PlaystationRoomDatabase.getDatabase(aplication).playstationDao()
        repository = PlaystationRepository(playstationDao)
        readAllData = repository.readAllData
    }

    fun addPlaystation(playstation: Playstation){
        viewModelScope.launch (Dispatchers.IO){
            repository.addPlaystation(playstation)
        }
    }

}
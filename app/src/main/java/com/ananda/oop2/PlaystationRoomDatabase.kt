package com.ananda.oop2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Playstation::class), version = 1)
abstract class PlaystationRoomDatabase : RoomDatabase() {
    abstract fun playstationDao(): PlaystationDao

    abstract val applicationContext: Context
    val db = Room.databaseBuilder(
        applicationContext,
        PlaystationRoomDatabase::class.java, "RENTAL_database"
    ).build()


    companion object{
        @Volatile
        private var INSTANCE: PlaystationRoomDatabase? = null

        fun getDatabase(context: Context): PlaystationRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaystationRoomDatabase::class.java,
                    "RENTAL_database")

                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
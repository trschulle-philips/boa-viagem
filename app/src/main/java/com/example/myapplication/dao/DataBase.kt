package com.example.myapplication.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.domain.Viagens
import com.example.myapplication.domain.Gastos

@Database(entities = arrayOf(Viagens::class, Gastos::class), exportSchema = true, version = 2)
abstract class DataBase() : RoomDatabase(){

    abstract fun viagensDao(): viagensDao
    abstract fun gastosDao(): gastosDao

    companion object{
        private var instance: com.example.myapplication.dao.DataBase? = null

        fun getInstance(context: Context): com.example.myapplication.dao.DataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    com.example.myapplication.dao.DataBase::class.java,
                    "da"
                )

                    .build()
            }
            return instance as com.example.myapplication.dao.DataBase
        }

    }
}

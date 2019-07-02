package com.example.myapplication.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Gastos(val tipoGasto: String, val valorGasto: Double, val dataGasto: String, val descricaoGasto: String, val localGasto: String) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}
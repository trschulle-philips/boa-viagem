package com.example.myapplication.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Viagens (val destino: String, val tipoViagem: String, val dataChegada: String, val dataPartida: String, val qtdOrcamento: String, val qtdPessoas: String){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}
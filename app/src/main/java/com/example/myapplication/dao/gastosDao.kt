package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.domain.Gastos
import com.example.myapplication.domain.Viagens
import com.google.common.net.HttpHeaders.FROM

@Dao
interface gastosDao {

     @Insert
     fun inserir(gastos: Gastos)

     @Update
     fun update(gastos: Gastos)

     @Delete
     fun delete(gastos: Gastos)

     @Query("select * from gastos order by id")
     fun findAll(): List<Gastos>

    @Query("SELECT SUM (valorGasto) from gastos WHERE dataGasto BETWEEN :inicial AND :final ;")
     fun getGastos( inicial: String, final: String): Double

}
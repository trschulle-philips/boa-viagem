package com.example.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.domain.Viagens

@Dao
interface viagensDao{

    @Insert
    fun inserir(viagens: Viagens)

    @Update
    fun update(viagens: Viagens)

    @Delete
    fun delete(viagens: Viagens)

    @Query("select * from viagens order by destino")
    fun findAll(): List<Viagens>

    @Query("select * from viagens where id =  :identificador" )
    fun findViagem(identificador: Double): Viagens

    @Query("DELETE FROM viagens")
    fun deletar()


}
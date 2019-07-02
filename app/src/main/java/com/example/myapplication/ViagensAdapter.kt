package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.dao.DataBase
import com.example.myapplication.dao.viagensDao
import com.example.myapplication.domain.Viagens

class ViagensAdapter(val dataset: List<Viagens>): RecyclerView.Adapter<ViagensViewHolder>() {
    var position = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViagensViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_lista_viagens, parent, false)
        return ViagensViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViagensViewHolder, position: Int) {
        holder.bind(dataset.get(position))
        holder.itemView.setOnClickListener{
            this.position = position
        }
        false
    }


    fun getViagem() : Viagens  {
        return this.dataset.get(position)
    }

}
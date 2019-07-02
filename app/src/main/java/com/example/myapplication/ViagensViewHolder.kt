package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.dao.DataBase
import com.example.myapplication.domain.Viagens


class ViagensViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){


    fun bind(viagens: Viagens){

        itemView.findViewById<TextView>(R.id.txLocal).text = "Destino: " + viagens.destino
        itemView.findViewById<TextView>(R.id.txChegada).text = "Dt Chegada:" + viagens.dataChegada
        itemView.findViewById<TextView>(R.id.txPartida).text = "Dt Partida:" + viagens.dataPartida
        itemView.findViewById<TextView>(R.id.txGastos).text = "RS 4.000"
    }
}
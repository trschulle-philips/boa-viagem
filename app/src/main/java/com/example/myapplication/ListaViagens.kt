package com.example.myapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.dao.DataBase
import com.example.myapplication.domain.Viagens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListaViagens : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_lista_viagens, container, false)

        val listaViagens = layout.findViewById<RecyclerView>(R.id.listaViagens)

        lateinit var adapter: ViagensAdapter

        GlobalScope.launch(Dispatchers.Main) {
            val dataset = withContext(Dispatchers.IO) {
                val viagensDao = DataBase.getInstance(layout.context).viagensDao()

                viagensDao.findAll()
            }
            listaViagens.adapter = ViagensAdapter(dataset)
        }

        val lista = layout.findViewById<RecyclerView>(R.id.listaViagens)

        lista.setOnClickListener{
            Toast.makeText(layout.context, "Viagens:",
                Toast.LENGTH_LONG).show()

        }



        listaViagens.layoutManager = LinearLayoutManager(layout.context, LinearLayoutManager.VERTICAL,false)
        return layout
    }


}

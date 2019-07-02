package com.example.myapplication


import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myapplication.dao.DataBase
import com.example.myapplication.domain.Viagens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NovaViagem : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        val layout = inflater.inflate(R.layout.activity_nova_viagem, container, false)

        //Trecjo para preencher os dados no campo "Destinos"
        val spDestino = layout.findViewById<Spinner>(R.id.spDestino)
        val rbLazer = layout.findViewById<RadioButton>(R.id.rbLazer)
        val btPartida = layout.findViewById<Button>(R.id.edPartida)
        val btChegada = layout.findViewById<Button>(R.id.edChegada)
        val edOrcamento = layout.findViewById<EditText>(R.id.edOrcamento)
        val edQtdPessoas = layout.findViewById<EditText>(R.id.edQtdPessoas)

        var tipoViagem = ""

        if(rbLazer.isChecked){
            tipoViagem = "Lazer"
        }else{
            tipoViagem = "Trabalho"
        }




        var list_of_items = arrayOf("Teste 01", "Teste 02", "Teste 03", "Teste 04")
        val adapter = ArrayAdapter(this.context, android.R.layout.simple_spinner_item,list_of_items)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spDestino.adapter = adapter;

        var cal = Calendar.getInstance()


        var atual = ""
        fun updateDateInView() {

            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            if(atual.equals("partida")) {
                btPartida.text = Editable.Factory.getInstance().newEditable(sdf.format(cal.time))
            }else{
                btChegada.text = Editable.Factory.getInstance().newEditable(sdf.format(cal.time))
            }
        }
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }


        btPartida.setOnClickListener {
            atual = "partida";
            DatePickerDialog(this@NovaViagem.context,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()

        }
        btChegada.setOnClickListener {
            atual = "chegada";
            DatePickerDialog(this@NovaViagem.context,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()

        }

        val btnNovaViagem = layout.findViewById<Button>(R.id.btNovaViagem);

        btnNovaViagem.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val viagens = withContext(Dispatchers.IO){
                    val viagensDao = DataBase.getInstance(layout.context).viagensDao()
                    val viagem = Viagens(spDestino.selectedItem.toString(),tipoViagem,btChegada.text.toString(),btPartida.text.toString(),edOrcamento.text.toString(),edQtdPessoas.text.toString())
                    viagensDao.inserir(viagem)
                    viagensDao.findAll()
                }
                Toast.makeText(layout.context, "Viagens: ${viagens}",
                    Toast.LENGTH_LONG).show()
                Log.d("viagens",viagens.toString())
            }

        }
        return layout
    }


}

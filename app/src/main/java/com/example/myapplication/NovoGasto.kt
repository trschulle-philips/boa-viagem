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
import com.example.myapplication.domain.Gastos
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
class NovoGasto : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_novo_gasto, container, false)

        val spTipo      = layout.findViewById<Spinner>(R.id.spTipo)
        val edValor     = layout.findViewById<EditText>(R.id.edValor)
        val btDataGasto = layout.findViewById<Button>(R.id.btDataGasto)
        val edDescricao = layout.findViewById<EditText>(R.id.edDescricao)
        val edLocal     = layout.findViewById<EditText>(R.id.edLocal)
        val btRegistrar = layout.findViewById<Button>(R.id.btRegistrar)


        var list_of_items = arrayOf("Alimentação", "Combustivel", "Transporte", "Hospedagem", "Outros")
        val adapter = ArrayAdapter(this.context, android.R.layout.simple_spinner_item,list_of_items)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spTipo.adapter = adapter;

        //Trecho referente a criação/uso do calendário
        var cal = Calendar.getInstance()



        fun updateDateInView() {

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            btDataGasto.text = Editable.Factory.getInstance().newEditable(sdf.format(cal.time))
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
        btDataGasto.setOnClickListener {
            DatePickerDialog(this@NovoGasto.context,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btRegistrar.setOnClickListener{
            GlobalScope.launch(Dispatchers.Main) {
                val gastos = withContext(Dispatchers.IO){
                    val gastosDao = DataBase.getInstance(layout.context).gastosDao()
                    val gastos = Gastos(spTipo.selectedItem.toString(),edValor.text.toString().toDouble(),btDataGasto.text.toString(),
                        edDescricao.text.toString(),edLocal.text.toString())
                    gastosDao.inserir(gastos)
                    gastosDao.findAll()
                }
                Toast.makeText(layout.context, "Viagens: ${gastos}",
                    Toast.LENGTH_LONG).show()
                Log.d("viagens",gastos.toString())
            }
        }
        return layout
    }


}

package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val edUsuario = findViewById<EditText>(R.id.edUsuario)
            val edSenha = findViewById<EditText>(R.id.edSenha)
            if ("".equals(edUsuario.text.trim().toString()) &&
                "".equals(edSenha.text.trim().toString())) {
                val menuIntent = Intent(this@MainActivity,
                    Menu::class.java)
                menuIntent.putExtra("usuario",
                    edUsuario.text.toString())

                startActivity(menuIntent)

            }
            else {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Login inválido")
                    .setMessage("Usuário/Senha inválidos")
                    .setPositiveButton("Ok", {dialog, i -> dialog.dismiss() })
                    .show()
            }

        }

    }
}

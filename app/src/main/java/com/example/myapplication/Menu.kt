package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.app.Fragment

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.framePrincipal, NovaViagem())
            .commit()

        findViewById<BottomNavigationView>(R.id.navegacao)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {

                    R.id.novaViagem -> createFragment (NovaViagem())
                    R.id.NovoGasto -> createFragment(NovoGasto())
                    R.id.ListaViagens -> createFragment(ListaViagens())

                    else -> false
                }
            }
    }

    private fun createFragment(f: androidx.fragment.app.Fragment): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.framePrincipal, f)
            .addToBackStack(null)
            .commit()
        return true
    }
}

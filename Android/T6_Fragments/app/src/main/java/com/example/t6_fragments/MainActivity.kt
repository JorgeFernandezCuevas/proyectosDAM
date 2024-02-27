package com.example.t6_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.View.OnClickListener
import com.example.t6_fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,OnClickListener, FragmentUno.OnFragmentUnoListener{

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //binding.sitioFragments
        // supportFragmentManager -> permite gestionar los fragments
        // supportFragmentManager -> fragmentTransation
        // 1. Crea el objeto de cuadro de dialogo
        // 2. show(supportFragmentManager, null)

        //val fm = supportFragmentManager // Permite gestionarlo
        //val ft = fm.beginTransaction() // Permite transacionar

        var ft = supportFragmentManager.beginTransaction()

        //replace
        ft.replace(binding.sitioFragments.id, FragmentUno(), "f1")
        ft.addToBackStack("f1")
        ft.commit()

        ft = supportFragmentManager.beginTransaction()

        ft.replace(binding.sitioFragments.id, FragmentUno(), "f11")
        ft.addToBackStack("f11")
        ft.commit()

        // add

        // remove
    }

    override fun onResume() {
        super.onResume()
        //binding.botonUno.setOnClickListener(this)
        //binding.botonDos.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v!!.id){
           /* R.id.botonUno->{
                var ft = supportFragmentManager.beginTransaction()
                //replace
                ft.replace(binding.sitioFragments.id, FragmentUno(), "f111")
                ft.addToBackStack("f111")
                ft.commit()
            }
            R.id.botonDos->{
                var ft = supportFragmentManager.beginTransaction()
                //replace
                ft.replace(binding.sitioFragments.id, FragmentDos(), "f2")
                ft.addToBackStack("f2")
                ft.commit()
            }*/
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNombreSelected(nombre: String) {
        supportActionBar!!.title = "Fragment Dos"
        var ft = supportFragmentManager.beginTransaction()
        //replace
        ft.replace(binding.sitioFragments.id, FragmentDos.newInstance(nombre), "f2")
        ft.addToBackStack("f2")
        ft.commit()
    }


}
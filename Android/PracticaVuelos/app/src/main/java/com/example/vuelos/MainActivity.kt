package com.example.vuelos

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.vuelos.adapters.AdaptadorRecycler
import com.example.vuelos.adapters.AdaptadorSpinner
import com.example.vuelos.databinding.ActivityMainBinding
import com.example.vuelos.model.Ciudad
import com.example.vuelos.model.Viaje

class MainActivity : AppCompatActivity() , TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaViajes:ArrayList<Viaje>
    private lateinit var listaCiudades:ArrayList<Ciudad>
    private lateinit var adaptadorSpinner: AdaptadorSpinner
    private lateinit var adaptadorRecycler: AdaptadorRecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listaViajes = ArrayList()
        listaCiudades = ArrayList()
        adaptadorSpinner = AdaptadorSpinner(listaCiudades,applicationContext)
        binding.listaCiudadIda.adapter =adaptadorSpinner
        binding.listaCiudadRegreso.adapter =adaptadorSpinner


    }

    override fun onStart() {
        super.onStart()
        listaCiudades.add(Ciudad("Madrid",R.drawable.madrid))
        listaCiudades.add(Ciudad("Barcelona",R.drawable.bcn))
        listaCiudades.add(Ciudad("Florencia",R.drawable.florencia))
        listaCiudades.add(Ciudad("L.A",R.drawable.la))
        listaCiudades.add(Ciudad("Londres",R.drawable.london))
        listaCiudades.add(Ciudad("Nueva York",R.drawable.newyork))
        listaCiudades.add(Ciudad("Paris",R.drawable.paris))
        listaCiudades.add(Ciudad("Roma",R.drawable.roma))
        listaCiudades.add(Ciudad("San Francisco",R.drawable.sanf))
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        binding.horaSalida.text = "$hourOfDay:$minute"

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    }
}
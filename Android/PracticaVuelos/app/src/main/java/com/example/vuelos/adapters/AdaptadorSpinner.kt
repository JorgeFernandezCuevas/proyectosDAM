package com.example.vuelos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.vuelos.R
import com.example.vuelos.model.Ciudad
import kotlinx.coroutines.NonDisposableHandle.parent

class AdaptadorSpinner(var lista:ArrayList<Ciudad>,var contexto: Context):BaseAdapter() {
    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(p0: Int): Any {
        return lista[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val vista :View = LayoutInflater.from(contexto).inflate(R.layout.item_spinner,p2,false)
        val ciudad = lista[p0]
        val imagen:ImageView = vista.findViewById(R.id.imagen_ciudad_spinner)
        var nombreCiudad:TextView = vista.findViewById(R.id.nombre_ciudad)
        imagen.setImageResource(ciudad.imagen)
        nombreCiudad.text = ciudad.nombre
        return vista
    }


}
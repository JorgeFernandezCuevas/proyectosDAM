package com.example.vuelos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.vuelos.R
import com.example.vuelos.model.Ciudad
import com.example.vuelos.model.Viaje

class AdaptadorRecycler(var lista:ArrayList<Viaje>,var contexto:Context) :RecyclerView.Adapter<AdaptadorRecycler.MyHolder>() {

    class MyHolder(item:View):ViewHolder(item){
        var destinos:TextView
        var imagen:ImageView
        var botonDetalle:Button
        init {
            destinos = item.findViewById(R.id.destinos)
            imagen = item.findViewById(R.id.imagen_ciudad)
            botonDetalle = item.findViewById(R.id.boton_detalle)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.item_recycler,parent,false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = lista[position]
        holder.imagen.setImageResource(item.imagen)
        holder.destinos.text = item.ciudad
    }

}
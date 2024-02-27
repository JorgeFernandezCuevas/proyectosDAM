package com.example.t4_json.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.t4_json.R
import com.example.t4_json.model.Usuario

class AdaptadorRecycler(var lista:ArrayList<Usuario>, var contexto :Context) :RecyclerView.Adapter<AdaptadorRecycler.MyHolder>() {
    class MyHolder(item : View) :ViewHolder(item){
        var texto:TextView
        init {
            texto =item.findViewById(R.id.texto)
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
        holder.texto.text = item.nombre
    }
}

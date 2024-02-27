package com.example.t6_navegation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.t6_navegation.R
import com.example.t6_navegation.model.Producto

class AdapterDetail(var lista:ArrayList<String>, var contexto: Context): RecyclerView.Adapter<AdapterDetail.MyHolder>() {

    class MyHolder(item:View): RecyclerView.ViewHolder(item){
        var imagen:ImageView
        init {
            imagen =item.findViewById(R.id.imagen_detalle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.detail_item,parent,false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = lista[position]
        Glide.with(contexto).load(item).placeholder(R.drawable.ic_launcher_background).into(holder.imagen)
    }


}
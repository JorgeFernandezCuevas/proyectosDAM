package com.example.compras.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.compras.R
import com.example.compras.model.Producto

class DetalleAdapter(var lista:ArrayList<String>,var context: Context):RecyclerView.Adapter<DetalleAdapter.MyHolder>(){
    class MyHolder(item:View):ViewHolder(item){
        var imagen:ImageView
        init {
            imagen =item.findViewById(R.id.imagen_detalle)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.detalle_item_recycler,parent,false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = lista[position]
        Glide.with(context).load(item).placeholder(R.drawable.ic_launcher_background).into(holder.imagen)
    }





}
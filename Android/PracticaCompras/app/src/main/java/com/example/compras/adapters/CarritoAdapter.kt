package com.example.compras.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.compras.R
import com.example.compras.model.Producto

class CarritoAdapter(var lista:ArrayList<Producto>,var contexto:Context):RecyclerView.Adapter<CarritoAdapter.MyHolder>() {

    class MyHolder(item:View):ViewHolder(item){
        var imagen:ImageView
        var nombre:TextView
        var precio:TextView
        var botonEliminar:Button
        init {
            imagen = item.findViewById(R.id.imagen_carrito)
            nombre = item.findViewById(R.id.nombre_carrito)
            precio = item.findViewById(R.id.precio_carrito)
            botonEliminar = item.findViewById(R.id.boton_eliminar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.producto_carrito,parent,false)
        return CarritoAdapter.MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = lista[position]
        Glide.with(contexto).load(item.thumbnail).placeholder(R.drawable.ic_launcher_background).into(holder.imagen)
        holder.nombre.text = item.nombre
        holder.precio.text =item.precio.toString()
        holder.botonEliminar.setOnClickListener {
            lista.remove(item)
            notifyItemRemoved(position)
        }

    }


}
package com.example.compras.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.compras.DetalleActivity
import com.example.compras.R
import com.example.compras.model.Producto

class MainAdapter(var lista:ArrayList<Producto>,var contexto: Context):RecyclerView.Adapter<MainAdapter.MyHolder>() {

    lateinit var listener: OnButtonComprarListener

    init {
        listener = contexto as OnButtonComprarListener
    }




    class MyHolder(item :View) :ViewHolder(item){
        var nombre:TextView
        var imagen:ImageView
        var botonDetalle:Button
        var botonComprar:Button
        init {
            nombre = item.findViewById(R.id.nombre_prodcto)
            imagen = item.findViewById(R.id.imagen_producto)
            botonDetalle = item.findViewById(R.id.boton_detalle)
            botonComprar = item.findViewById(R.id.boton_comprar)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.main_item_recycler,parent,false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = lista[position]
        Glide.with(contexto).load(item.thumbnail).placeholder(R.drawable.ic_launcher_background).into(holder.imagen)
        holder.nombre.text = item.nombre
        holder.botonDetalle.setOnClickListener{
            val intent = Intent(contexto,DetalleActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("producto",item)
            contexto.startActivity(intent)
        }
        holder.botonComprar.setOnClickListener {
            listener.onButtonComprarClick(item)
        }
    }
    interface OnButtonComprarListener {
        fun onButtonComprarClick(producto: Producto)
    }


}
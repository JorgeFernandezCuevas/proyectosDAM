package com.example.t6_navegation.adapters

import android.content.Context
import android.location.Geocoder.GeocodeListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.t6_navegation.R
import com.example.t6_navegation.model.Producto
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AdapterProductos(var contexto:Context,var uid:String) :RecyclerView.Adapter<AdapterProductos.MyHolder>(){

    private lateinit var listaProductos:ArrayList<Producto>
    var database = Firebase.database

    init {
        listaProductos = ArrayList()
        database = FirebaseDatabase.getInstance("https://jfc-ces-default-rtdb.europe-west1.firebasedatabase.app/")

    }
    class MyHolder( item :View):ViewHolder(item){
        var toolbar: androidx.appcompat.widget.Toolbar
        var imageView: ImageView

        init {
            toolbar = item.findViewById(R.id.toolbar_carta)
            imageView = item.findViewById(R.id.imagen_producto)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.item_productos,parent,false)
        return MyHolder(vista)
    }
    override fun getItemCount(): Int {
        return this.listaProductos.size
    }
    fun addProducto(producto: Producto){
        listaProductos.add(producto)
        notifyItemInserted(listaProductos.size-1)
    }
    fun borrarLista(){
        listaProductos.clear()
    }
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item :Producto =listaProductos[position]
        holder.toolbar.title = item.title
        holder.toolbar.inflateMenu(R.menu.item_menu)
        holder.toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.menu_fav_item->{
                    val referencia =database.getReference("usuarios").child(uid).child("favoritos").child(item.title!!)
                   referencia.child("precio").setValue(item.price)
                   referencia.child("descripcion").setValue(item.description)
                   referencia.child("categoria").setValue(item.category)
                   referencia.child("imagenes").setValue(item.images)
                }
                R.id.menu_detail_item->{


                }
            }

            return@setOnMenuItemClickListener true
        }
        Glide.with(contexto).load(item.thumbnail).into(holder.imageView)
    }
}
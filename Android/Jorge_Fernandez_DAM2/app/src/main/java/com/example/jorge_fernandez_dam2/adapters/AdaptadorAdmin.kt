package com.example.jorge_fernandez_dam2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jorge_fernandez_dam2.R
import com.example.jorge_fernandez_dam2.model.Usuario

class AdaptadorAdmin (var context: Context, var lista:ArrayList<Usuario>) :RecyclerView.Adapter<AdaptadorAdmin.MyHolder>(){
    class MyHolder(view:View):ViewHolder(view){
        var correo :TextView
        var pass:TextView
        init {
            correo = view.findViewById(R.id.correo_usuario)
            pass =view.findViewById(R.id.pass_usuario)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var vista = LayoutInflater.from(context).inflate(R.layout.item_admin,parent,false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = lista[position]
        holder.correo.text = item.correo
        holder.pass.text =item.pass

    }


}
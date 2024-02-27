package com.example.compras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.compras.adapters.DetalleAdapter
import com.example.compras.databinding.ActivityDetalleBinding
import com.example.compras.model.Producto

class DetalleActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetalleBinding
    private lateinit var adaptadorDetalle :DetalleAdapter
    private lateinit var producto:Producto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        producto = intent.getSerializableExtra("producto") as Producto


        adaptadorDetalle = DetalleAdapter(producto.imagenes,applicationContext)
        binding.listaDetalle.adapter = adaptadorDetalle
        binding.listaDetalle.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL,false)



        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.detalleNombre.text = producto.nombre
        binding.detallePrecio.text = producto.precio.toString()
        binding.detalleStock.text = producto.stock.toString()
        binding.detalleDescripcion.text = producto.descripcion

    }
}
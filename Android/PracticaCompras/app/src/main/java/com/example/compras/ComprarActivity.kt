package com.example.compras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.compras.adapters.CarritoAdapter
import com.example.compras.databinding.ActivityComprarBinding
import com.example.compras.databinding.ActivityMainBinding
import com.example.compras.model.Producto
import com.google.android.material.snackbar.Snackbar

class ComprarActivity : AppCompatActivity(),OnClickListener {
    private lateinit var binding: ActivityComprarBinding
    private lateinit var lista:ArrayList<Producto>
    private lateinit var adapter: CarritoAdapter
    private  var precioTotal:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComprarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lista = ArrayList()

        lista = intent.getSerializableExtra("lista") as ArrayList<Producto>
        adapter = CarritoAdapter(lista,this)
        binding.productosAgregados.adapter = adapter
        binding.productosAgregados.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.boton_comprar->{
                lista.forEach {
                    precioTotal+=it.precio
                }
                Toast.makeText(applicationContext,"Compra realizada por valor de ${precioTotal}€",Toast.LENGTH_LONG).show()
            }
        }
    }

}




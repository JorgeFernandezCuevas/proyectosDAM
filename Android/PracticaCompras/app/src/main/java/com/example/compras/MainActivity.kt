package com.example.compras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.View.OnClickListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.compras.adapters.MainAdapter
import com.example.compras.databinding.ActivityMainBinding
import com.example.compras.model.Producto
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(),OnClickListener,MainAdapter.OnButtonComprarListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var productos:ArrayList<Producto>
    private lateinit var adapter: MainAdapter
    private  var contador:Int=0
    private lateinit var listaCarrito:ArrayList<Producto>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listaCarrito = ArrayList()
        productos = ArrayList()
        adapter = MainAdapter(productos,this)
        binding.listaProductos.adapter = adapter
        binding.listaProductos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        getUrlResponse("https://dummyjson.com/products")
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.botonCarrito.setOnClickListener(this)
    }

    fun getUrlResponse(url:String){
        val peticion : JsonObjectRequest = JsonObjectRequest(url,{
            getProducto(it)
        },{

        })
        //val peticion2 :JsonObjectRequest = JsonObjectRequest(Method.GET, url,null,{},{})
        Volley.newRequestQueue(applicationContext).add(peticion)
    }
    fun getProducto(response:JSONObject){
        var resultados:JSONArray =response.getJSONArray("products")
        for (i in 0..resultados.length()-1){
            var producto :JSONObject = resultados.getJSONObject(i)
            var nombre:String = producto.getString("title")
            var precio:Int = producto.getInt("price")
            var stock:Int = producto.getInt("stock")
            var descripcion:String = producto.getString("description")
            var thumbnail:String = producto.getString("thumbnail")
            var imagenesResultado:JSONArray =producto.getJSONArray("images")
            var imagenes :ArrayList<String> = ArrayList()
            Log.v("imagenes",nombre)
            for (j in 0..imagenesResultado.length()-1){
                imagenes.add(imagenesResultado.getString(j))
            }
            productos.add(Producto(nombre,precio,stock,descripcion,thumbnail, imagenes))
            adapter.notifyItemInserted(productos.size-1)
        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.botonCarrito.id->{
                val intent = Intent(applicationContext,ComprarActivity::class.java)
                intent.putExtra("lista",listaCarrito)
                startActivity(intent)
            }
        }
    }

    override fun onButtonComprarClick(producto: Producto) {
        contador+=1
        binding.contadorCarrito.text = contador.toString()
        listaCarrito.add(producto)


    }


}
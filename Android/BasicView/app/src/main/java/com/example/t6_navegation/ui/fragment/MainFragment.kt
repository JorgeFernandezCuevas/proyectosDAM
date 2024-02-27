package com.example.t6_navegation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.t6_navegation.R
import com.example.t6_navegation.adapters.AdapterProductos
import com.example.t6_navegation.databinding.FragmentFirstBinding
import com.example.t6_navegation.databinding.LoginFragmentBinding
import com.example.t6_navegation.databinding.MainFragmentBinding
import com.example.t6_navegation.databinding.SingFragmentBinding
import com.example.t6_navegation.model.Producto
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private lateinit var adapterProductos: AdapterProductos
    private lateinit var database:FirebaseDatabase
    private lateinit var uid:String
    private lateinit var producto: Producto


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        uid=arguments?.getString("uid").toString()
        adapterProductos= AdapterProductos(context,arguments?.getString("uid").toString())
        //producto = arguments?.getSerializable("producto") as Producto
        database = FirebaseDatabase.getInstance("https://jfc-ces-default-rtdb.europe-west1.firebasedatabase.app/")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }
    fun getUrlResponse(url:String){
        val peticion : JsonObjectRequest = JsonObjectRequest(url,{
            getProducto(it)
        },{

        })
        //val peticion2 :JsonObjectRequest = JsonObjectRequest(Method.GET, url,null,{},{})
        Volley.newRequestQueue(context).add(peticion)
    }
    fun getProducto(response: JSONObject){
        var resultados: JSONArray =response.getJSONArray("products")
        for (i in 0..resultados.length()-1){
            var imagenes:ArrayList<String> = ArrayList()
            var producto : JSONObject = resultados.getJSONObject(i)
            var nombre:String = producto.getString("title")
            var precio:Double = producto.getDouble("price")
            var category:String = producto.getString("category")
            var descripcion:String = producto.getString("description")
            var thumbnail:String = producto.getString("thumbnail")
            var imagenesResultado = producto.getJSONArray("images")
            for (j in 0..imagenesResultado.length()-1){
                imagenes.add(imagenesResultado.getString(j))
            }
            //Log.v("imagenes", imagenes.toString())
            adapterProductos.addProducto(Producto(nombre,precio,descripcion,category,thumbnail,imagenes))
        }
    }
    fun getAllProductsDB(){
        val reference=  database.getReference("datos").child("products").orderByChild("title")
        reference.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.v("datos",snapshot.toString())
                val hijos = snapshot.children
                hijos.forEach{
                    var producto = it.getValue(Producto::class.java)!!
                    adapterProductos.addProducto(producto)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //toda la parte grafica esta asociada

        binding.textoNombre.text=arguments?.getString("nombre")
        adapterProductos.borrarLista()
        getAllProductsDB()



        binding.recyclerProductos.adapter = adapterProductos
        binding.recyclerProductos.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        //getUrlResponse("https://dummyjson.com/products")

        database.getReference("nombre").setValue("Prueba")
        database.getReference("apellido").setValue("Prueba")

        binding.botonEscuchar.setOnClickListener {

            var nombreProducto=binding.nombreCambiar.text.toString()
            var precioProducto = binding.precioCambiar.text.toString().toInt()

            val reference=  database.getReference("datos").child("products").orderByChild("title").equalTo(nombreProducto).addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                   if (snapshot.exists()){
                       var hijo = snapshot.children.forEach{
                           it.children.forEach{
                               if (it.key.equals("price")){
                                   it.ref.setValue(precioProducto)
                               }
                           }
                       }
                   }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
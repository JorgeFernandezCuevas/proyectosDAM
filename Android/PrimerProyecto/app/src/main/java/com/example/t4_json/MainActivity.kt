package com.example.t4_json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.t4_json.adapter.AdaptadorRecycler
import com.example.t4_json.adapters.AdaptadorRecycler
import com.example.t4_json.databinding.ActivityMainBinding
import com.example.t4_json.model.Usuario
import com.example.t4_json.ui.dialog.DialogoPersonalizado
import com.example.t4_json.ui.dialog.FiltrarDialog
import com.example.t4_json.ui.dialog.GeneroDialog
import com.example.t4_json.ui.dialog.GeneroListaDialog
import com.example.t4_json.ui.dialog.GeneroSimpleDialog
import com.example.t4_json.ui.dialog.InformacionDialog
import com.example.t4_json.ui.dialog.NacionalDialog
import com.example.t4_json.ui.dialog.NumeroDialog
import com.example.t4_json.ui.dialog.VersionDialog
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import org.json.JSONObject

    class MainActivity : AppCompatActivity() ,FiltrarDialog.OnFiltradoDialogListener,GeneroDialog.OnGeneroDialogListener,DialogoPersonalizado.OnDialogoPersonalizadoListener, NumeroDialog.OnNumeroDialogListener, GeneroListaDialog.OnGeneroListaListener,GeneroSimpleDialog.OnGeneroSimpleListener,NacionalDialog.OnNacionalidadListener{
        private lateinit var binding: ActivityMainBinding
        private lateinit var listaUsuarios:ArrayList<Usuario>
        private lateinit var adaptadorRecycler: AdaptadorRecycler

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)


            listaUsuarios = ArrayList()
            adaptadorRecycler = AdaptadorRecycler(listaUsuarios,applicationContext)
            binding.lista.adapter = adaptadorRecycler
            binding.lista.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
            //creo peticion
            getUrlResponse("https://randomuser.me/api/")



        }
        fun getUrlResponse(url:String){
            val peticion :JsonObjectRequest = JsonObjectRequest(url,{
                getUsers(it)
            },{

            })
            //val peticion2 :JsonObjectRequest = JsonObjectRequest(Method.GET, url,null,{},{})
            Volley.newRequestQueue(applicationContext).add(peticion)
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu,menu)
            return super.onCreateOptionsMenu(menu)

        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.menu_reset->{

                }
                R.id.menu_version->{
                    val versionDialog:VersionDialog = VersionDialog()
                    versionDialog.show(supportFragmentManager,null)
                }
                R.id.menu_filtrar->{
                    val dialogoPersonalizado:DialogoPersonalizado = DialogoPersonalizado()
                    dialogoPersonalizado.show(supportFragmentManager,null)
                }
                R.id.menu_ayuda->{
                    val informacionDialog :InformacionDialog = InformacionDialog.newInstance("Jorhe",19)
                    informacionDialog.show(supportFragmentManager,null)
                }
            }
            return true
        }


        fun getUsers(response:JSONObject):Unit{
            //Obtener una lista de todos los usuarios del Json
            // nombre, apellido, telefono, pais, ciudad, cp, mail
            listaUsuarios.clear()
            adaptadorRecycler.notifyDataSetChanged()
            var resultados:JSONArray = response.getJSONArray("results")
            for (i in 0 .. resultados.length()-1){
                var usuario :JSONObject = resultados.getJSONObject(i)
                var objetoNombre:JSONObject = usuario.getJSONObject("name")
                var nombre = objetoNombre.getString("first")
                var apellido = objetoNombre.getString("last")
                var telefono = usuario.getString("phone")
                var objetoLocation:JSONObject = usuario.getJSONObject("location")
                var pais = objetoLocation.getString("country")
                var ciudad= objetoLocation.getString("city")
                var cp = objetoLocation.getString("postcode")
                var mail = usuario.getString("email")
                var usuarioFinal:Usuario = Usuario(nombre,apellido,telefono,pais,ciudad,cp,mail)
                listaUsuarios.add(usuarioFinal)
                adaptadorRecycler.notifyItemInserted(listaUsuarios.size-1)
            }
        }

        override fun onOpcionSelected(opcion: String) {
            if (opcion.equals("genero")){
                /*val generoDialog=GeneroDialog()
                generoDialog.show(supportFragmentManager,null)*/

                /*val generoListaDialog = GeneroListaDialog()
                generoListaDialog.show(supportFragmentManager,null)*/

                val generoSimpleDialog = GeneroSimpleDialog()
                generoSimpleDialog.show(supportFragmentManager,null)


            }else{
                val numeroDialog = NumeroDialog()
                numeroDialog.show(supportFragmentManager,null)
            }
        }

        override fun onGeneroSelected(genero: String) {
            getUrlResponse("https://randomuser.me/api/?gender=${genero}")
        }

        override fun onNumeroSelected(numero: String) {
            getUrlResponse("https://randomuser.me/api/?results=${numero}")
        }

        override fun onGeneroListaSelected() {
        }

        override fun onGeneroSimpleSelected(genero: String) {
            Toast.makeText(applicationContext,genero.toString(),Toast.LENGTH_LONG).show()
        }

        override fun onDialogoNacionalidadSelected(nacionalidades: ArrayList<String>) {

        }

        override fun onGeneroNumeroPersoSelected(genero: String, numero: String) {
            getUrlResponse("https://randomuser.me/api/?results=${numero}&gender=${genero.toLowerCase()}")

        }
    }
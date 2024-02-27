package com.example.jorge_fernandezcuevas_dam2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jorge_fernandezcuevas_dam2.R
import com.example.jorge_fernandezcuevas_dam2.databinding.FragmentFirstBinding
import com.example.jorge_fernandezcuevas_dam2.databinding.LoginFragmentBinding
import com.example.jorge_fernandezcuevas_dam2.databinding.MainFragmentBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentMain : Fragment() {

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonCrear.setOnClickListener {
            val database = Firebase.database("https://jfc-ces-default-rtdb.europe-west1.firebasedatabase.app/")
            var nombreUsuario =arguments?.getString("nombre")
            var uid= arguments?.getString("uid")?:""

            var productos =database.getReference("productos").child(uid)
            productos.child("id").setValue(binding.idProducto.text.toString())
            productos.child("nombre").setValue(binding.nombreProducto.text.toString())
            productos.child("precio").setValue(binding.precioProducto.text.toString())
            productos.child("categoria").setValue(binding.categorias.selectedItem.toString())
            productos.child("stock").setValue(binding.stockProducto.text.toString())
            productos.child("descripcion").setValue(binding.descProducto.text.toString())

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
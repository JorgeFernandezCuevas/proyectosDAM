package com.example.t6_navegation.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.t6_navegation.R
import com.example.t6_navegation.databinding.FragmentFirstBinding
import com.example.t6_navegation.databinding.LoginFragmentBinding
import com.example.t6_navegation.databinding.SingFragmentBinding
import com.google.android.material.snackbar.Snackbar

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SinginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: SingFragmentBinding? = null
    private lateinit var database: FirebaseDatabase

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://jfc-ces-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SingFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.botonCrear.setOnClickListener {
            findNavController().navigate(R.id.action_singinFragment_to_loginFragment)
        }
        binding.botonCrearIniciar.setOnClickListener {
            findNavController().navigate(R.id.action_singinFragment_to_mainFragment)
        }
        binding.botonCrear.setOnClickListener {
            if (binding.crearPass.text.toString()==binding.confirmarPass.text.toString()){
                auth.createUserWithEmailAndPassword(binding.crearCorreo.text.toString(),binding.crearPass.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        Snackbar.make(binding.root,"Cuenta creada con exito",Snackbar.LENGTH_LONG).show()
                        val referencia =database.getReference("usuarios").child(auth.currentUser?.uid.toString())
                        referencia.child("nombre").setValue(binding.crearNombre.text.toString())
                        referencia.child("apellido").setValue(binding.crearApellido.text.toString())
                        referencia.child("correo").setValue(binding.crearCorreo.text.toString())
                        referencia.child("direccion").setValue(binding.crearDireccion.text.toString())
                    }else{
                        Snackbar.make(binding.root,"Fallo en la creacion de la cuenta",Snackbar.LENGTH_LONG).show()
                    }
                }
            }else{
                Snackbar.make(binding.root,"Pass diferentes",Snackbar.LENGTH_LONG).show()

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.jorge_fernandez_dam2.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jorge_fernandez_dam2.R
import com.example.jorge_fernandez_dam2.databinding.FragmentFirstBinding
import com.example.jorge_fernandez_dam2.databinding.FragmentRegistroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RegistroFragment : Fragment() {

    private var _binding: FragmentRegistroBinding? = null
    private lateinit var auth:FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private  var contador: Int =0


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth= Firebase.auth
        database = FirebaseDatabase.getInstance("https://jfc-ces-default-rtdb.europe-west1.firebasedatabase.app/")

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonRegistro.setOnClickListener {
            var reference = database.getReference("usuariosExamen")
            auth.createUserWithEmailAndPassword(binding.editCorreo.text.toString(),binding.editPass.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    var refe2= reference.child(auth.currentUser!!.uid)
                    refe2.child("correo").setValue(binding.editCorreo.text.toString())
                    refe2.child("pass").setValue(binding.editPass.text.toString())
                    refe2.child("perfil").setValue(binding.sipnnerPerfil.selectedItem.toString())
                    Snackbar.make(binding.root,"Registro correcto",Snackbar.LENGTH_LONG).show()
                    val bundle=Bundle()
                    bundle.putString("perfil",binding.sipnnerPerfil.selectedItem.toString())
                    findNavController().navigate(R.id.action_registroFragment_to_loginFragment,bundle)
                }else{
                    Snackbar.make(binding.root,"Error",Snackbar.LENGTH_LONG).show()
                }
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
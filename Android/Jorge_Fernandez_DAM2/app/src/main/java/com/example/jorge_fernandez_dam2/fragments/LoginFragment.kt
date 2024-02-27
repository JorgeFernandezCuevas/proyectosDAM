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
import com.example.jorge_fernandez_dam2.databinding.FragmentLoginBinding
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
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
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

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(binding.inicioCorreo.text.toString(),binding.inicioPass.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    var perfil = arguments?.getString("perfil")
                    val bundle = Bundle()
                    bundle.putString("perfil",perfil.toString())
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment,bundle)

                    Snackbar.make(binding.root,"Datos Correctos",Snackbar.LENGTH_LONG).show()
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
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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private lateinit var auth: FirebaseAuth
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

        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.botonInicio.setOnClickListener {

            auth.signInWithEmailAndPassword(binding.nombreUsuario.text.toString(),binding.passUsuario.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    var bundle = Bundle()
                    bundle.putString("nombre",binding.nombreUsuario.text.toString())
                    bundle.putString("uid",auth.uid.toString())


                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment,bundle)
                }else{
                    Snackbar.make(binding.root,"Fallo en el inicio de sesion.", Snackbar.LENGTH_LONG).show()
                }
            }
            //

       }
        binding.botonRegistrar.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_singinFragment)
        }
        binding.botonInvitado.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
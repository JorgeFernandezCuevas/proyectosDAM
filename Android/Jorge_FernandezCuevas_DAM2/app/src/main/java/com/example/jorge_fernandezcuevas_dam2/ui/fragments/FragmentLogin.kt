package com.example.jorge_fernandezcuevas_dam2.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jorge_fernandezcuevas_dam2.R
import com.example.jorge_fernandezcuevas_dam2.databinding.FragmentFirstBinding
import com.example.jorge_fernandezcuevas_dam2.databinding.LoginFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentLogin : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private lateinit var auth: FirebaseAuth;


    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = Firebase.auth
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

        binding.botonIniciar.setOnClickListener {
            auth.signInWithEmailAndPassword(binding.nombreUsuario.text.toString(),binding.passUsuario.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    val bundle = Bundle()
                    bundle.putString("nombre",binding.nombreUsuario.text.toString())
                    findNavController().navigate(R.id.action_fragmentLogin_to_fragmentMain,bundle)
                }else{
                    auth.createUserWithEmailAndPassword(binding.nombreUsuario.text.toString(),binding.passUsuario.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            val bundle = Bundle()
                            bundle.putString("nombre",binding.nombreUsuario.text.toString())
                            bundle.putString("uid",auth.uid.toString())
                            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentMain)
                        }else{
                            Snackbar.make(binding.root,"Error",Snackbar.LENGTH_LONG).show()
                        }

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
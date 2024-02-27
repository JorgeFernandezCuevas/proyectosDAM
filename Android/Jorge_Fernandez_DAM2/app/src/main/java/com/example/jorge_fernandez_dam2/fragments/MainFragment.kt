package com.example.jorge_fernandez_dam2.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jorge_fernandez_dam2.R
import com.example.jorge_fernandez_dam2.adapters.AdaptadorAdmin
import com.example.jorge_fernandez_dam2.databinding.FragmentFirstBinding
import com.example.jorge_fernandez_dam2.databinding.FragmentMainBinding
import com.example.jorge_fernandez_dam2.databinding.FragmentRegistroBinding
import com.example.jorge_fernandez_dam2.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private lateinit var auth:FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private  var contador: Int =0
    private lateinit var adapterAdmin :AdaptadorAdmin
    private lateinit var listaUsuarios:ArrayList<Usuario>


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

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView2.text=arguments?.getString("perfil")

        var reference = database.getReference("usuariosExamen").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var hijo =snapshot.children
                hijo.forEach {
                    // me falta el add del hijo al la lista y pasar la lista al adptador y se pintaria

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
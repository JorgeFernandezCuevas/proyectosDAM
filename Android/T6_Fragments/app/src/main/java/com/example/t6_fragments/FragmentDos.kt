package com.example.t6_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.t6_fragments.databinding.FragmentDosBinding
import com.example.t6_fragments.databinding.FragmentUnoBinding

class FragmentDos:Fragment() {

    private lateinit var contexto : Context
    private lateinit var binding: FragmentDosBinding
    private lateinit var nombreRecuperado:String

    companion object{
        fun newInstance(nombre:String):FragmentDos{
            val fragmentDos = FragmentDos()
            val bundle = Bundle()
            bundle.putString("nombre",nombre)
            fragmentDos.arguments = bundle
            return fragmentDos
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contexto = context
        nombreRecuperado=this.arguments?.getString("nombre") ?:"sin nombre"

        //Iniciar las interfaces de callback

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.textoNombre.text = nombreRecuperado
    }

    override fun onDetach() {
        super.onDetach()
        //elimina cualquier dependencia

    }
}
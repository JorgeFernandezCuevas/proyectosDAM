package com.example.t4_json.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t4_json.R

class DialogoPersonalizado:DialogFragment() {
    private lateinit var contexto:Context
    private  var listener:OnDialogoPersonalizadoListener?=null
    private lateinit var vista:View
    private lateinit var spinnerGeneros:Spinner
    private lateinit var spinnerNumeros:Spinner
    private lateinit var adapterNumeros:ArrayAdapter<Int>
    private lateinit var boton:Button
    private lateinit var generoSeleccionado:String
    private lateinit var numeroSeleccionado:String
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contexto= context
        this.vista= LayoutInflater.from(context).inflate(R.layout.dialogo_personalizado,null)
        //this.listener = this.contexto as OnDialogoPersonalizadoListener
        this.adapterNumeros = ArrayAdapter(context,android.R.layout.simple_spinner_item,(1..100).toList())
        this.adapterNumeros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

    }

    override fun onDetach() {
        super.onDetach()
        this.listener = null

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder= AlertDialog.Builder(contexto)
        builder.setView(vista)
        spinnerNumeros= vista.findViewById(R.id.sipnner_numeros)
        spinnerNumeros.adapter = adapterNumeros
        spinnerGeneros = vista.findViewById(R.id.sipnner_generos)
        boton = vista.findViewById(R.id.boton)
        boton.setOnClickListener {
            generoSeleccionado= spinnerGeneros.selectedItem.toString()
            numeroSeleccionado= spinnerNumeros.selectedItem.toString()
            listener?.onGeneroNumeroPersoSelected(generoSeleccionado,numeroSeleccionado)
            dismiss()
        }

        return builder.create()
    }
    interface OnDialogoPersonalizadoListener{
        fun onGeneroNumeroPersoSelected(genero:String,numero:String)

    }

}
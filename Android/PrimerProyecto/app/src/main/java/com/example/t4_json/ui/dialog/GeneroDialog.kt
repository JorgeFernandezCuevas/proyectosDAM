package com.example.t4_json.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class GeneroDialog:DialogFragment() {
    private lateinit var contexto: Context
    private  var listener: OnGeneroDialogListener?=null

    override fun onAttach(context: Context) {
        this.contexto = context
        super.onAttach(context)
        this.listener= this.contexto as OnGeneroDialogListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.contexto)
        builder.setTitle("Filtrado de generos")
        builder.setMessage("¿Porque genero quieres filtrar?")
        builder.setPositiveButton("Masculino") { _, _ -> listener?.onGeneroSelected("male") }
        builder.setNegativeButton("Femenino") { _, _ -> listener?.onGeneroSelected("female") }
        builder.setNeutralButton("Todos") { _, _ -> listener?.onGeneroSelected("all") }
        return builder.create()
    }

    override fun onDetach() {
        super.onDetach()
        this.listener=null
    }

    interface OnGeneroDialogListener{
        fun onGeneroSelected(genero:String)

    }
}
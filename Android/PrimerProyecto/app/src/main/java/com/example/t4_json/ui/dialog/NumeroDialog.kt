package com.example.t4_json.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class NumeroDialog:DialogFragment() {
    private lateinit var contexto: Context
    private  var listener: OnNumeroDialogListener?=null

    override fun onAttach(context: Context) {
        this.contexto = context
        super.onAttach(context)
        this.listener= this.contexto as OnNumeroDialogListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.contexto)
        builder.setTitle("Filtrado de generos")
        builder.setMessage("¿Porque genero quieres filtrar?")
        builder.setPositiveButton("100") { _, _ -> listener?.onNumeroSelected("100") }
        builder.setNegativeButton("50") { _, _ -> listener?.onNumeroSelected("50") }
        builder.setNeutralButton("1") { _, _ -> listener?.onNumeroSelected("1") }
        return builder.create()
    }

    override fun onDetach() {
        super.onDetach()
        this.listener=null
    }

    interface OnNumeroDialogListener{
        fun onNumeroSelected(numero:String)

    }

}
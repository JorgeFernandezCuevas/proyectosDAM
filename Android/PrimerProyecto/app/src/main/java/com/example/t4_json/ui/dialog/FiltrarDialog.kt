package com.example.t4_json.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class FiltrarDialog:DialogFragment() {
    private lateinit var contexto:Context
    private  var listener: OnFiltradoDialogListener?=null

    override fun onAttach(context: Context) {
        this.contexto = context
        super.onAttach(context)
        this.listener= this.contexto as OnFiltradoDialogListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder:AlertDialog.Builder = AlertDialog.Builder(this.contexto)
        builder.setTitle("Filtrado de generos")
        builder.setMessage("¿Porque genero quieres filtrar?")
        builder.setPositiveButton("Genero") { _, _ -> listener?.onOpcionSelected("genero") }
        builder.setNegativeButton("Numero") { _, _ -> listener?.onOpcionSelected("numero") }

        return builder.create()
    }

    override fun onDetach() {
        super.onDetach()
        this.listener=null
    }

    interface OnFiltradoDialogListener{
        fun onOpcionSelected(opcion:String)

    }

}
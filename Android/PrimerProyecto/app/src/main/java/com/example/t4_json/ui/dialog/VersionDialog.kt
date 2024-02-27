package com.example.t4_json.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class VersionDialog :DialogFragment() {
    private lateinit var contexto:Context
    override fun onAttach(context: Context) {
        this.contexto = context
        super.onAttach(context)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder:AlertDialog.Builder = AlertDialog.Builder(contexto)

        builder.setTitle("App listas JSON")
        builder.setMessage("Version 1.0 de la app realizda por JFC")
        builder.setPositiveButton("OK"){ _, _ -> Toast.makeText(this.contexto,"Pulsado OK",Toast.LENGTH_LONG).show() }

        return builder.create()
    }

}
package com.example.t4_json.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t4_json.R

class GeneroListaDialog :DialogFragment(){
    private  var listener:OnGeneroListaListener?=null
    private lateinit var context: Context
    override fun onAttach(context: Context) {
        this.context = context
        super.onAttach(context)
        this.listener = this.context as OnGeneroListaListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder :AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Porque genero quieres filtrar")
        builder.setItems(R.array.generos){_,posicion->
            val genero = resources.getStringArray(R.array.generos)
            Toast.makeText(this.context,genero.toString(),Toast.LENGTH_LONG).show()


        }


        return builder.create()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnGeneroListaListener{
        fun onGeneroListaSelected()

    }
}
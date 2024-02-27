package com.example.t4_json.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t4_json.R

class GeneroSimpleDialog :DialogFragment(){
    private  var listener:OnGeneroSimpleListener?=null
    private lateinit var context: Context
    override fun onAttach(context: Context) {
        this.context = context
        super.onAttach(context)
        this.listener = this.context as OnGeneroSimpleListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder :AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Porque genero quieres filtrar")
        lateinit var genero :String
        builder.setSingleChoiceItems(R.array.generos,-1,){_,pos->
            genero = resources.getStringArray(R.array.generos)[pos]
            //la ejecucion
        }
        builder.setPositiveButton("ok"){_,_,-> listener?.onGeneroSimpleSelected(genero) }


        return builder.create()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnGeneroSimpleListener{
        fun onGeneroSimpleSelected(genero:String)

    }
}
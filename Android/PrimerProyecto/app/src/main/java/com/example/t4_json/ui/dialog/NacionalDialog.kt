package com.example.t4_json.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t4_json.R

class NacionalDialog:DialogFragment() {
    private lateinit var context: Context
    private var listener: OnNacionalidadListener? = null
    private  var listaNacionalidades:ArrayList<String> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Selecciona las nacionalidades a mostrar")
        builder.setMultiChoiceItems(R.array.nacionalidades, null)
        { _, pos, isChecked ->
            // ejecuto
            if (isChecked){
                listaNacionalidades.add(resources.getStringArray(R.array.nacionalidades)[pos])
            }else{
                listaNacionalidades.remove(resources.getStringArray(R.array.nacionalidades)[pos])
            }

        }
        builder.setPositiveButton("OK") { _, _ ->
            // comunico
            listener?.onDialogoNacionalidadSelected(listaNacionalidades)

        }
        return builder.create()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnNacionalidadListener {
        fun onDialogoNacionalidadSelected(nacionalidades: ArrayList<String>)
    }

}
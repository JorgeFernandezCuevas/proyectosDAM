package com.example.t4_json.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t4_json.R

class InformacionDialog:DialogFragment(){
    private lateinit var contexto:Context
    private lateinit var vista:View
    private lateinit var texto:TextView
    private lateinit var textoComunicar:String
    private  var edadComunicar :Int =0

    companion object{
        fun newInstance(nombre:String,edad:Int):InformacionDialog{
            val informacionDialog:InformacionDialog = InformacionDialog()
            val bundle = Bundle()
            bundle.putString("nombre",nombre)
            bundle.putInt("edad",edad)

            informacionDialog.arguments = bundle

            return informacionDialog
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        this.contexto = context
        vista = LayoutInflater.from(contexto).inflate(R.layout.info_dialog,null)
        textoComunicar=this.arguments?.getString("nombre")?:"sin nombre"
        edadComunicar = this.arguments?.getInt("edad")?:0

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(contexto)
        builder.setView(vista)
        texto = vista.findViewById(R.id.nombre_usuario)
        texto.text = textoComunicar +" ${edadComunicar}"



        return builder.create()

    }

    override fun onDetach() {
        super.onDetach()
    }

}
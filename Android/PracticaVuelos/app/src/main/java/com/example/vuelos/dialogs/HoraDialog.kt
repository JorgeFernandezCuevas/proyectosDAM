package com.example.vuelos.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class HoraDialog : DialogFragment() {

    private lateinit var contexto: Context
    private var horaActual = 0;
    private var minutosActual = 0;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contexto = context;

        val calendar = Calendar.getInstance()
        horaActual = calendar.get(Calendar.HOUR_OF_DAY)
        minutosActual = calendar.get(Calendar.MINUTE)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        /*buider: title, messaje, positive*/
        return TimePickerDialog(
            contexto, contexto as OnTimeSetListener,
            horaActual, minutosActual, true
        )
    }

    override fun onDetach() {
        super.onDetach()
    }

}
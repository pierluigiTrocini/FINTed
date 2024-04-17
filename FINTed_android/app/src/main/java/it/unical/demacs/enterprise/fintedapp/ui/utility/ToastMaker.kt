package it.unical.demacs.enterprise.fintedapp.ui.utility

import android.content.Context
import android.widget.Toast

object ToastMaker {
    fun makeToast(context: Context, string: String){
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }
}
package it.unical.demacs.enterprise.fintedapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import it.unical.demacs.enterprise.fintedapp.ui.utility.AppIndex

object CurrentIndex {
    val appIndex: MutableState<AppIndex> = mutableStateOf(AppIndex.HOMEPAGE)

    val currentDialogOpen: MutableState<Long?> = mutableStateOf(null)
}
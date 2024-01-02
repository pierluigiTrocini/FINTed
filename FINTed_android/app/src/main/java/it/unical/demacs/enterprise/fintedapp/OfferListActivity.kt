package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.material.Text

@Composable
fun OfferListActivity(context: Context, selectedIndex: MutableState<Int>) {
    Text(text = stringResource(id = R.string.offers))
}

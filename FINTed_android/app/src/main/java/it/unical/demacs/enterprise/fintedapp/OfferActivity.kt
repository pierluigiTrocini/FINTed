package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import it.unical.demacs.enterprise.fintedapp.viewmodels.`OfferViewModel.kt`
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfferActivity(
    sheetState: MutableState<Boolean>,
    context: Context,
    selectedIndex: MutableState<Index>,
    coroutineScope: CoroutineScope,
    userViewModel: UserViewModel,
    `anOfferViewModel.kt`: `OfferViewModel.kt`,
    postId: Long?
){
}



private fun makeToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}
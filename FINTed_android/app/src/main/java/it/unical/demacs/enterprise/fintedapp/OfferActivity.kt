package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.material.Text
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfferActivity(
    sheetState: MutableState<Boolean>,
    post: PostDto?,
    context: Context,
    selectedIndex: MutableState<Index>,
    coroutineScope: CoroutineScope
){
    val offerPrice = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { sheetState.value = false }
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                androidx.compose.material3.Text(
                    text = stringResource(id = R.string.offerTitle),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                OutlinedTextField(
                    value = offerPrice.value,
                    onValueChange = {
                            v ->
                        if(v.all{ it.isDigit() }){
                            offerPrice.value = v
                        }
                    },
                    label = { Text(stringResource(id = R.string.makeOfferLabel)) },
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                Button(onClick = {
                    selectedIndex.value = Index.HOMEPAGE
                    coroutineScope.launch {
                        sheetState.value = false
                        makeToast(context, context.resources.getString(R.string.offerPublishedToast))
                    }
                }) {
                    Text(stringResource(id = R.string.publish))
                }
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}



private fun makeToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}
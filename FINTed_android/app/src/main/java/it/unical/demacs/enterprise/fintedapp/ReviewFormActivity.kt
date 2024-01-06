package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.models.ReviewDto
import it.unical.demacs.enterprise.fintedapp.models.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewFormActivity(
    sheetState: MutableState<Boolean>,
    context: Context,
    selectedIndex: MutableState<Index>,
    coroutineScope: CoroutineScope,
    author: UserDto?,
    target: UserDto?
){
    val text = remember { mutableStateOf("") }

    ModalBottomSheet(onDismissRequest = { sheetState.value = false }) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = stringResource(id = R.string.reviewTitle),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
                OutlinedTextField(
                    value = text.value,
                    onValueChange = {
                        v -> text.value = v
                    },
                    maxLines = 10
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                Button(onClick = {
                    coroutineScope.launch {
                        sheetState.value = false
                        makeToast(context, context.resources.getString(R.string.reviewPublishedToast))
                    }
                }) {
                    androidx.wear.compose.material.Text(stringResource(id = R.string.publish))
                }
            }
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}

private fun makeToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}
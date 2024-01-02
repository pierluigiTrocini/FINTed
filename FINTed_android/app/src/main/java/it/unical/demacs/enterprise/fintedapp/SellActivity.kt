package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text

@Composable
fun SellActivity(
    context: Context,
    selectedIndex: MutableState<Int>
){
    var title = remember { mutableStateOf("") }
    var startingPrice = remember { mutableStateOf("") }

    val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
        type = "*/*"
        addCategory(Intent.CATEGORY_OPENABLE)
    }

    val postImage = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){}

    Column(modifier = Modifier.padding(20.dp)) {
        Row() {
            OutlinedTextField(
                value = title.value,
                onValueChange = { v -> title.value = v },
                label = { Text(stringResource(id = R.string.postTitle)) },
                maxLines = 1
            )
        }
        Row() {
            OutlinedTextField(
                value = startingPrice.value,
                onValueChange = {
                                v ->
                    if(v.all{ it.isDigit() }){
                        startingPrice.value = v
                    }
                },
                label = { Text(stringResource(id = R.string.portStartingPrice)) },
                maxLines = 1
            )
        }

        Row(modifier = Modifier.padding(40.dp)){
            Button(onClick = { postImage.launch(intent) }) {
                Text(stringResource(id = R.string.postAddImage))
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
            Button(onClick = {
                makeToast(context, "articolo venduto")
                selectedIndex.value = 0
            }) {
                Text(stringResource(id = R.string.publish))
            }
        }

    }
}

private fun makeToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}

package it.unical.demacs.enterprise.fintedapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SellActivity(
    context: Context,
    selectedIndex: MutableState<Index>,
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>
){
    val title = remember { mutableStateOf("") };
    val startingPrice = remember { mutableStateOf("") };
    val imageContent = remember { mutableStateOf("") }

    val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
        type = "*/*"
        addCategory(Intent.CATEGORY_OPENABLE)
    }

    val postImage = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result -> imageContent.value = postViewModel.value.updateImage(result, context)
    }

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
                label = { Text(stringResource(id = R.string.postStartingPrice)) },
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        if(imageContent.value.isEmpty()){
            Text(text = stringResource(id = R.string.noImage))
        }
        else{
            Image(bitmap = postViewModel.value.decodeImageToBitmap(imageContent.value),
                contentDescription = stringResource(id = R.string.image))
        }


        Row(modifier = Modifier.padding(40.dp)){
            Button(onClick = { postImage.launch(intent) }) {
                Text(stringResource(id = R.string.postAddImage))
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
            Button(onClick = {
                makeToast(context, context.resources.getString(R.string.postPublishing))
                selectedIndex.value = Index.HOMEPAGE

                postViewModel.value.publishPost(
                    title = title.value,
                    startingPrice = startingPrice.value,
                    postImage = imageContent.value,
                    seller = userViewModel.value.personalProfile.value
                )
            }) {
                Text(stringResource(id = R.string.publish))
            }
        }

    }
}

private fun makeToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}

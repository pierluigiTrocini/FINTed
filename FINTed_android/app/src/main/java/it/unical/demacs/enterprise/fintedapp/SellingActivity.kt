package it.unical.demacs.enterprise.fintedapp

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.ui.utility.AppIndex
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun SellingActivity(
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>,
    appIndex: MutableState<AppIndex>
){
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) {uri: Uri? ->
        imageUri.value = uri
    }

    var title = remember { mutableStateOf("") }
    var startingPrice = remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.sell),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text(stringResource(id = R.string.postTitle)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = startingPrice.value,
            onValueChange = { startingPrice.value = it },
            label = { Text(stringResource(id = R.string.postStartingPrice)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = stringResource(id = R.string.postAddImage))
        }

        imageUri.value?.let { uri ->
            val bitmap = context.contentResolver.openInputStream(uri)?.use { 
                inputStream -> BitmapFactory.decodeStream(inputStream)
            }
            bitmap?.let { 
                Image(bitmap = it.asImageBitmap(), contentDescription = stringResource(id = R.string.postImage))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            postViewModel.value.save(
                title = title.value,
                startingPrice = startingPrice.value.toLong(),
                postImage = postViewModel.value.convertImageToBase64(imageUri = imageUri.value!!, context = context)
            )
            appIndex.value = AppIndex.HOMEPAGE
        }) {
            Text(stringResource(id = R.string.publish))
        }
    }
}
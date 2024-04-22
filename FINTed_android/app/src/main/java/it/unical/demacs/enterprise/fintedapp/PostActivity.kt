package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel

@Composable
fun PostActivity(
    post: PostDto,
    postViewModel: PostViewModel,
    offerViewModel: OfferViewModel
) {
    val offerState = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            //TODO image
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.title ?: stringResource(id = R.string.unavailable),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.postStartingPrice) + post.startingPrice
                    ?: stringResource(id = R.string.unavailable),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                offerState.value = true
            }) {
                Text(text = stringResource(id = R.string.makeOffer))
            }
        }
    }

    if (offerState.value) {
        val offerText = remember { mutableStateOf("") }

        Dialog(onDismissRequest = { offerState.value = false }) {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(modifier = Modifier.padding(10.dp)){
                    Column {
                        Text(
                            text = stringResource(id = R.string.makeOffer),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        OutlinedTextField(
                            value = offerText.value,
                            onValueChange = { v -> offerText.value = v },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(stringResource(id = R.string.makeOffer)) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {
                            offerState.value = false
                        }) {
                            Text(text = stringResource(id = R.string.publish))
                        }
                    }
                }
            }
        }
    }

}
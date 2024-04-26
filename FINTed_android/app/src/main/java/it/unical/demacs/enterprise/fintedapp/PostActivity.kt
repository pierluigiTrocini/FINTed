package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.AuthValues
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.ReviewViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun PostActivity(
    post: PostDto,
    postViewModel: MutableState<PostViewModel>,
    offerViewModel: MutableState<OfferViewModel>,
    userViewModel: MutableState<UserViewModel>,
    reviewViewModel: MutableState<ReviewViewModel>,
    scope: PostActivityScope
) {
    val showProfile = remember { mutableStateOf(false) }
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
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.seller) + (post.sellerUsername
                    ?: stringResource(
                        id = R.string.unavailable
                    ))),
                onClick = { showProfile.value = true },
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.postStartingPrice) + " : " + post.startingPrice
                    ?: stringResource(id = R.string.unavailable),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (scope == PostActivityScope.HOMEPAGE && post.sellerUsername != AuthValues.username.value) {
                Button(onClick = {
                    offerState.value = true
                }) {
                    Text(text = stringResource(id = R.string.makeOffer))
                }
            }
        }
    }

    if (showProfile.value) {
        Dialog(onDismissRequest = { showProfile.value = false }) {
            userViewModel.value.get(username = post.sellerUsername!!)

            ProfileActivity(
                userViewModel = userViewModel,
                postViewModel = postViewModel,
                offerViewModel = offerViewModel,
                reviewViewModel = reviewViewModel,
                scope = ProfileActivityScope.BASIC_USER
            )
        }
    }

    if (offerState.value) {
        val offerText = remember { mutableStateOf("") }

        Dialog(onDismissRequest = { offerState.value = false }) {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(modifier = Modifier.padding(10.dp)) {
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
                            offerViewModel.value.save(
                                postId = post.id!!,
                                userUsername = AuthValues.username.value!!,
                                offerLong = offerText.value.toLong()
                            )
                        }) {
                            Text(text = stringResource(id = R.string.publish))
                        }
                    }
                }
            }
        }
    }

}

enum class PostActivityScope {
    HOMEPAGE, PERSONAL_PROFILE
}
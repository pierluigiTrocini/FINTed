package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import it.unical.demacs.enterprise.fintedapp.ui.utility.AppIndex
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun OfferActivity(
    offer: OfferDto,
    offerViewModel: MutableState<OfferViewModel>,
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>,
    scope: OfferActivityScope
) {
    val showPost = remember { mutableStateOf(false) }
    val showProfile = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            ClickableText(text = AnnotatedString(
                (stringResource(id = R.string.offerPostTitle) + offer.postTitle)
            ), style = MaterialTheme.typography.titleLarge,
                onClick = { showPost.value = true })
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.offerPostedBy) + offer.postSellerUsername),
                style = MaterialTheme.typography.titleMedium,
                onClick = { showProfile.value = true }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = (stringResource(id = R.string.offerOfferLong) + offer.offer),
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }

    if (showPost.value) {
        postViewModel.value.get(offer.postId!!)

        Dialog(onDismissRequest = { showPost.value = false }) {
            PostActivity(
                post = postViewModel.value.post.value,
                postViewModel = postViewModel,
                offerViewModel = offerViewModel,
                scope = PostActivityScope.PERSONAL_PROFILE
            )
        }
    }

    if(showProfile.value) {
        userViewModel.value.get(offer.postSellerUsername!!)

        Dialog(onDismissRequest = { showProfile.value = false }) {
            ProfileActivity(
                userViewModel = userViewModel,
                postViewModel = postViewModel,
                offerViewModel = offerViewModel,
                scope = ProfileActivityScope.BASIC_USER
            )
        }

    }
}

enum class OfferActivityScope {
    PUBLISHED_OFFERS
}
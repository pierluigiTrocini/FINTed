package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.ReviewViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun OfferActivity(
    offer: OfferDto,
    offerViewModel: MutableState<OfferViewModel>,
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>,
    reviewViewModel: MutableState<ReviewViewModel>,
    scope: OfferActivityScope
) {
    val showPost = remember { mutableStateOf(false) }
    val showSellerProfile = remember { mutableStateOf(false) }
    val showUserProfile = remember { mutableStateOf(false) }


    val showSpeditionCode = remember { mutableStateOf(false) }

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

            if(scope == OfferActivityScope.PUBLISHED_OFFERS){
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.offerPostedBy) + offer.postSellerUsername),
                    style = MaterialTheme.typography.titleMedium,
                    onClick = { showSellerProfile.value = true }
                )
            }
            if(scope == OfferActivityScope.RECV_OFFERS) {
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.offerUserUsername) + offer.userUsername),
                    style = MaterialTheme.typography.titleMedium,
                    onClick = { showUserProfile.value = true }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = (stringResource(id = R.string.offerOfferLong) + offer.offer),
                style = MaterialTheme.typography.bodyLarge
            )

            if (offer.offerStatus != null) {
                Spacer(modifier = Modifier.height(16.dp))

                when (offer.offerStatus) {
                    OfferDto.OfferStatus.PENDING -> {
                        Text(
                            text = stringResource(id = R.string.offerStatusPending),
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    OfferDto.OfferStatus.ACCEPTED -> {
                        Text(
                            text = stringResource(id = R.string.offerStatusAccepted),
                            color = Color.Green,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    OfferDto.OfferStatus.DENIED -> {
                        Text(
                            text = stringResource(id = R.string.offerStatusDenied),
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (scope == OfferActivityScope.RECV_OFFERS && offer.offerStatus == OfferDto.OfferStatus.PENDING) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            offerViewModel.value.acceptOffer(offer)
                            showSpeditionCode.value = true
                        }
                    ) {
                        Text(text = stringResource(id = R.string.acceptOffer))
                    }
                    FilledTonalButton(onClick = { offerViewModel.value.denyOffer(offer) }) {
                        Text(text = stringResource(id = R.string.denyOffer))
                    }
                }
            }
        }
    }

    if (showSpeditionCode.value) {
        Dialog(onDismissRequest = { showSpeditionCode.value = false }) {
            SpeditionActivity(spedition = offerViewModel.value.spedition.value, scope = SpeditionActivityScope.POPUP)
        }
    }

    if (showPost.value) {
        postViewModel.value.get(offer.postId!!)

        Dialog(onDismissRequest = { showPost.value = false }) {
            PostActivity(
                post = postViewModel.value.post.value,
                postViewModel = postViewModel,
                offerViewModel = offerViewModel,
                userViewModel = userViewModel,
                reviewViewModel = reviewViewModel,
                scope = PostActivityScope.PERSONAL_PROFILE
            )
        }
    }

    if (showSellerProfile.value) {
        userViewModel.value.get(offer.postSellerUsername!!)

        Dialog(onDismissRequest = { showSellerProfile.value = false }) {
            ProfileActivity(
                userViewModel = userViewModel,
                postViewModel = postViewModel,
                offerViewModel = offerViewModel,
                reviewViewModel = reviewViewModel,
                scope = ProfileActivityScope.BASIC_USER
            )
        }
    }

    if (showUserProfile.value) {
        userViewModel.value.get(offer.userUsername!!)

        Dialog(onDismissRequest = { showSellerProfile.value = false }) {
            ProfileActivity(
                userViewModel = userViewModel,
                postViewModel = postViewModel,
                offerViewModel = offerViewModel,
                reviewViewModel = reviewViewModel,
                scope = ProfileActivityScope.BASIC_USER
            )
        }
    }
}

enum class OfferActivityScope {
    PUBLISHED_OFFERS, RECV_OFFERS
}
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
import it.unical.demacs.enterprise.fintedapp.models.ReviewDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.ReviewViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun ReviewActivity(
    review: ReviewDto,
    reviewViewModel: MutableState<ReviewViewModel>,
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>,
    offerViewModel: MutableState<OfferViewModel>
){
    val showProfile = remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.reviewAuthor) + review.authorUsername ?: stringResource(
                    id = R.string.unavailable
                )),
                style = MaterialTheme.typography.titleMedium,
                onClick = { showProfile.value = true }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = (review.content ?: stringResource(id = R.string.unavailable)),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    if(showProfile.value){
        userViewModel.value.get(review.authorUsername!!)

        Dialog(onDismissRequest = { showProfile.value = false }) {
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

enum class ReviewActivityScope {
    PUBLISHED_REVIEWS, RECEIVED_REVIEWS
}
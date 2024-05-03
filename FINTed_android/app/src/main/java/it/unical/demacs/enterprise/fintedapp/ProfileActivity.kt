package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import it.unical.demacs.enterprise.fintedapp.models.PostBasicInfoDto
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.models.ReviewDto
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserProfileDto
import it.unical.demacs.enterprise.fintedapp.ui.utility.AppIndex
import it.unical.demacs.enterprise.fintedapp.viewmodels.AuthValues
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.ReviewViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun ProfileActivity(
    context: Context = LocalContext.current,
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>,
    offerViewModel: MutableState<OfferViewModel>,
    reviewViewModel: MutableState<ReviewViewModel>,
    scope: ProfileActivityScope
) {
    userViewModel.value.getPersonal(username = AuthValues.username.value)

    val profile: MutableState<UserPersonalProfileDto> = userViewModel.value.personalProfile
    val profileIndex = remember { mutableStateOf(PersonalProfileIndex.POSTS) }
    val basicUser: MutableState<UserProfileDto> = userViewModel.value.basicUser
    val showReviewForm = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.padding(2.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(id = R.string.profile),
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    text = if (scope == ProfileActivityScope.PERSONAL_PROFILE) profile.value.username
                        ?: stringResource(id = R.string.unavailable)
                    else basicUser.value.username ?: stringResource(id = R.string.unavailable),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Text(
                        text = if (scope == ProfileActivityScope.PERSONAL_PROFILE) profile.value.firstName
                            ?: stringResource(id = R.string.unavailable)
                        else basicUser.value.firstName ?: stringResource(id = R.string.unavailable),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (scope == ProfileActivityScope.PERSONAL_PROFILE) profile.value.lastName
                            ?: stringResource(id = R.string.unavailable)
                        else basicUser.value.lastName ?: stringResource(id = R.string.unavailable),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (scope == ProfileActivityScope.PERSONAL_PROFILE) profile.value.credentialsEmail
                        ?: stringResource(id = R.string.unavailable)
                    else basicUser.value.credentialsEmail
                        ?: stringResource(id = R.string.unavailable),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
        if (scope == ProfileActivityScope.PERSONAL_PROFILE) {
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = { profileIndex.value = PersonalProfileIndex.POSTS }) {
                    Text(text = stringResource(id = R.string.profilePostList))
                }

                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = { profileIndex.value = PersonalProfileIndex.OFFERS }) {
                    Text(text = stringResource(id = R.string.profileOfferList))
                }

                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = { profileIndex.value = PersonalProfileIndex.RECV_REVIEWS }) {
                    Text(text = stringResource(id = R.string.profileRecvReviews))
                }

                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = { profileIndex.value = PersonalProfileIndex.SENT_REVIEWS }) {
                    Text(text = stringResource(id = R.string.profileSentReviews))
                }
            }
            Row(horizontalArrangement = Arrangement.Center) {
                if (profileIndex.value == PersonalProfileIndex.POSTS) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (profile.value.publishedPosts == null || profile.value.publishedPosts!!.isEmpty()) {
                            Spacer(modifier = Modifier.height(40.dp))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Filled.List,
                                    contentDescription = stringResource(id = R.string.postList),
                                    modifier = Modifier.size(75.dp)
                                )
                                Text(text = stringResource(id = R.string.noPosts))
                                Button(onClick = { CurrentIndex.appIndex.value = AppIndex.SELL }) {
                                    Text(text = stringResource(id = R.string.sell))
                                }
                            }
                        } else {
                            LazyColumn(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(items = profile.value.publishedPosts!!,
                                    key = { post: PostBasicInfoDto -> post.id!! }) { post: PostBasicInfoDto ->
                                    PostBasicInfoActivity(
                                        post = post,
                                        postViewModel = postViewModel,
                                        userViewModel = userViewModel,
                                        offerViewModel, reviewViewModel
                                    )
                                }
                            }
                        }
                    }
                } else if (profileIndex.value == PersonalProfileIndex.OFFERS) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (profile.value.publishedOffers == null || profile.value.publishedOffers!!.isEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.List,
                                contentDescription = stringResource(id = R.string.yourOfferList),
                                modifier = Modifier.size(75.dp)
                            )
                            Text(text = stringResource(id = R.string.noOffers))
                        } else {
                            LazyColumn(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(items = profile.value.publishedOffers!!,
                                    key = { offer: OfferDto -> offer.id!! }) { offer: OfferDto ->
                                    OfferActivity(
                                        offer = offer,
                                        offerViewModel = offerViewModel,
                                        scope = OfferActivityScope.PUBLISHED_OFFERS,
                                        userViewModel = userViewModel,
                                        postViewModel = postViewModel,
                                        reviewViewModel = reviewViewModel
                                    )
                                }
                            }
                        }
                    }
                } else if (profileIndex.value == PersonalProfileIndex.RECV_REVIEWS) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (profile.value.receivedReviews == null || profile.value.receivedReviews!!.isEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.List,
                                contentDescription = stringResource(id = R.string.profileRecvReviews),
                                modifier = Modifier.size(75.dp)
                            )
                            Text(text = stringResource(id = R.string.noReviews))
                        } else {
                            LazyColumn(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(items = profile.value.receivedReviews!!,
                                    key = { review: ReviewDto -> review.id!! }) { review: ReviewDto ->
                                    ReviewActivity(
                                        review,
                                        reviewViewModel,
                                        userViewModel,
                                        postViewModel,
                                        offerViewModel,
                                        scope = ReviewActivityScope.RECEIVED_REVIEWS
                                    )
                                }
                            }
                        }
                    }
                }
                else if (profileIndex.value == PersonalProfileIndex.SENT_REVIEWS) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (profile.value.publishedReviews == null || profile.value.publishedReviews!!.isEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.List,
                                contentDescription = stringResource(id = R.string.profileRecvReviews),
                                modifier = Modifier.size(75.dp)
                            )
                            Text(text = stringResource(id = R.string.noReviews))
                        } else {
                            LazyColumn(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(items = profile.value.publishedReviews!!,
                                    key = { review: ReviewDto -> review.id!! }) { review: ReviewDto ->
                                    ReviewActivity(
                                        review,
                                        reviewViewModel,
                                        userViewModel,
                                        postViewModel,
                                        offerViewModel,
                                        scope = ReviewActivityScope.PUBLISHED_REVIEWS
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (scope == ProfileActivityScope.BASIC_USER && basicUser.value.username != AuthValues.username.value) {
                Spacer(modifier = Modifier.height(64.dp))
                Button(onClick = { showReviewForm.value = true }) {
                    Text(text = stringResource(id = R.string.makeReview))
                }

                if (showReviewForm.value) {
                    Dialog(onDismissRequest = { showReviewForm.value = false }) {
                        ReviewFormActivity(
                            showReviewForm = showReviewForm,
                            reviewViewModel = reviewViewModel,
                            targetUsername = basicUser.value.username!!
                        )
                    }
                }
            }
        }
    }
}

enum class ProfileActivityScope {
    PERSONAL_PROFILE, BASIC_USER
}


enum class PersonalProfileIndex {
    POSTS,
    SENT_REVIEWS,
    RECV_REVIEWS,
    OFFERS

}
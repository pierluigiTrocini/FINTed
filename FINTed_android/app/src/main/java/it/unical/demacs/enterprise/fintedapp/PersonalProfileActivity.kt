package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.material.Icon
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.ReviewViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import java.text.SimpleDateFormat

@Composable
fun PersonalProfileActivity(
    context: Context,
    selectedIndex: MutableState<Index>,
    accountState: MutableState<AccountState>,
    coroutineScope: CoroutineScope,
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>,
    offerViewModel: MutableState<OfferViewModel>,
    postSheetStates: SnapshotStateMap<Long, Boolean>,
    offerSheetStates: SnapshotStateMap<Long, OfferInfos>,
    reviewViewModel: MutableState<ReviewViewModel>,
) {
    val profile = userViewModel.value.personalProfile.value
    val userPosts = userViewModel.value.personalProfile.value.publishedPosts
    val userOffers = userViewModel.value.personalProfile.value.publishedOffers
    val receivedReviews = userViewModel.value.personalProfile.value.receivedReviews
    val publishedReviews = userViewModel.value.personalProfile.value.publishedReviews

    val profileIndex = remember { mutableStateOf(ProfileIndex.POSTS) }

    val showUpdateForm = remember { mutableStateOf(false) }

    accountState.value = AccountState.LOGGED

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clip(CardDefaults.shape),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(id = R.string.profile),
                            modifier = Modifier.size(70.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = profile?.username.toString(),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = profile?.firstName.toString() + "   " + profile?.lastName.toString(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.emailLabel) + profile?.credentialsEmail.toString(),
                            modifier = Modifier.padding(vertical = 10.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(text = stringResource(id = R.string.registrationDate) + "\t" + profile?.registrationDate?.let {
                            SimpleDateFormat("dd/MM/yyy").format(
                                it
                            ).toString()
                        }, style = MaterialTheme.typography.bodySmall)
                        Text(
                            text = stringResource(id = R.string.balance) + "\t" + profile?.balance.toString() + "\t" + stringResource(
                                id = R.string.currency
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.itemId) + "\t #" + profile?.id.toString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    Button(onClick = { showUpdateForm.value = false }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(id = R.string.edit),
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }
                    Button(onClick = {
                        userViewModel.value.logout(context = context)
                    }) {
                        Text(text = stringResource(id = R.string.logout))
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState(), enabled = true),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { profileIndex.value = ProfileIndex.POSTS },
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(stringResource(id = R.string.postList))
            }
            Button(
                onClick = { profileIndex.value = ProfileIndex.OFFERS },
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(stringResource(id = R.string.yourOfferList))
            }
            Button(
                onClick = { profileIndex.value = ProfileIndex.RECEIVED_REVIEWS },
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(stringResource(id = R.string.yourReceivedReviews))
            }
            Button(
                onClick = { profileIndex.value = ProfileIndex.SENT_REVIEWS },
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(stringResource(id = R.string.yourSentReviews))
            }
        }
        if (profileIndex.value == ProfileIndex.POSTS) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (userPosts != null && userPosts.isEmpty()) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = stringResource(id = R.string.postList),
                            modifier = Modifier.size(75.dp)
                        )
                        Text(text = stringResource(id = R.string.noPosts))
                        Button(onClick = { selectedIndex.value = Index.SELL }) {
                            Text(text = stringResource(id = R.string.sell))
                        }
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (userPosts != null) {
                            items(
                                items = userPosts.toList(),
                                key = { post -> post.id!! }
                            ) { post ->
                                PostActivity(
                                    post = post,
                                    coroutineScope = coroutineScope,
                                    context = context,
                                    selectedIndex = selectedIndex,
                                    postType = PostType.FOR_PERSONAL_PROFILE,
                                    userViewModel = userViewModel,
                                    offerViewModel = offerViewModel,
                                    postSheetStates = postSheetStates
                                )
                            }
                        }
                    }
                }
            }
        } else if (profileIndex.value == ProfileIndex.OFFERS) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (userOffers != null && userOffers.isEmpty()) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = stringResource(id = R.string.yourOfferList),
                            modifier = Modifier.size(75.dp)
                        )
                        Text(text = stringResource(id = R.string.noOffers))
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (userOffers != null) {
                            items(
                                items = userOffers.toList(),
                                key = { offer -> offer.id!! }
                            ) { offer ->

                                offerSheetStates[offer.id!!] = OfferInfos.NONE

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp),
                                    shape = MaterialTheme.shapes.medium,
                                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                                    elevation = CardDefaults.cardElevation(8.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Column() {
                                            Row() {
                                                ClickableText(
                                                    text = AnnotatedString(offer.postTitle!! + "[" + offer.postId + "]"),
                                                    style = MaterialTheme.typography.titleLarge.copy(
                                                        fontWeight = FontWeight.Bold
                                                    ),
                                                    modifier = Modifier.padding(horizontal = 10.dp),
                                                    onClick = {
                                                        offerSheetStates[offer.id!!] =
                                                            OfferInfos.POST
                                                    },
                                                )
                                            }
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(text = stringResource(id = R.string.offerPrice) + offer.offer.toString() + "€")
                                            }
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(
                                                    text = stringResource(id = R.string.offerStatus),
                                                    style = MaterialTheme.typography.bodyMedium.copy(
                                                        fontWeight = FontWeight.Bold
                                                    ),
                                                    modifier = Modifier.padding(horizontal = 10.dp)
                                                )
                                                when (offer.offerStatus) {
                                                    OfferDto.OfferStatus.ACCEPTED -> {
                                                        Text(
                                                            text = stringResource(id = R.string.offerStatusAccepted),
                                                            color = Color.Green,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                        Text(
                                                            text = stringResource(id = R.string.delivering),
                                                            color = Color.Green,
                                                            style = MaterialTheme.typography.labelSmall
                                                        )
                                                    }

                                                    OfferDto.OfferStatus.DENIED -> {
                                                        Text(
                                                            text = stringResource(id = R.string.offerStatusDenied),
                                                            color = Color.Red,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                        Text(
                                                            text = stringResource(id = R.string.creditedBack),
                                                            color = Color.Red,
                                                            style = MaterialTheme.typography.labelSmall
                                                        )
                                                    }

                                                    OfferDto.OfferStatus.POST_DELETED -> {
                                                        Text(
                                                            text = stringResource(id = R.string.offerStatusPostDeleted),
                                                            color = Color.Yellow,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                        Text(
                                                            text = stringResource(id = R.string.refund),
                                                            color = Color.Yellow,
                                                            style = MaterialTheme.typography.labelSmall
                                                        )
                                                    }

                                                    OfferDto.OfferStatus.PENDING -> {
                                                        Text(
                                                            text = stringResource(id = R.string.offerStatusPending),
                                                            color = Color.Blue,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    }

                                                    OfferDto.OfferStatus.UNAVAILABLE -> {
                                                        Text(
                                                            text = stringResource(id = R.string.offerStatusUnavailable),
                                                            color = Color.Cyan,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                        Text(
                                                            text = stringResource(id = R.string.refund),
                                                            color = Color.Cyan,
                                                            style = MaterialTheme.typography.labelSmall
                                                        )
                                                    }

                                                    else -> {}
                                                }
                                            }
                                        }
                                        if (offerSheetStates[offer.id!!] != OfferInfos.NONE) {
                                            if (offerSheetStates[offer.id!!] == OfferInfos.POST) {
                                                postViewModel.value.get(offer.postId!!)

                                                Dialog(onDismissRequest = {
                                                    offerSheetStates[offer.id!!] = OfferInfos.NONE
                                                }) {
                                                    PostActivity(
                                                        post = postViewModel.value.post.value,
                                                        coroutineScope = coroutineScope,
                                                        context = context,
                                                        selectedIndex = selectedIndex,
                                                        postType = PostType.FOR_PERSONAL_PROFILE,
                                                        userViewModel = userViewModel,
                                                        offerViewModel = offerViewModel,
                                                        postSheetStates = null
                                                    )
                                                }
                                            } else if (offerSheetStates[offer.id!!] == OfferInfos.USER) {
                                                userViewModel.value.getUser(offer.userUsername!!)

                                                Dialog(onDismissRequest = {
                                                    offerSheetStates[offer.id!!] = OfferInfos.NONE
                                                }) {
                                                    ProfileActivity(
                                                        context = context,
                                                        selectedIndex = selectedIndex,
                                                        profile = userViewModel.value.basicUser.value,
                                                        reviewViewModel = reviewViewModel
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (profileIndex.value == ProfileIndex.RECEIVED_REVIEWS) {
            if (receivedReviews != null) {
                if (receivedReviews.isEmpty()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "",
                            modifier = Modifier.size(75.dp)
                        )
                        androidx.wear.compose.material.Text(
                            text = stringResource(id = R.string.noReviews),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(
                            items = receivedReviews.toList(),
                            key = { review -> review.id!! }
                        ) { review ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
                            ) {
                                Card(
                                    modifier = Modifier.padding(16.dp),
                                    shape = RoundedCornerShape(8.dp),
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column {
                                            Text(
                                                text = review.authorUsername!!,
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = Color.Gray
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = review.content!!,
                                                style = MaterialTheme.typography.labelMedium
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (profileIndex.value == ProfileIndex.SENT_REVIEWS) {
            if (publishedReviews != null) {
                if (publishedReviews.isEmpty()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "",
                            modifier = Modifier.size(75.dp)
                        )
                        androidx.wear.compose.material.Text(
                            text = stringResource(id = R.string.noReviews),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(
                            items = publishedReviews.toList(),
                            key = { review -> review.id!! }
                        ) { review ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
                            ) {
                                Card(
                                    modifier = Modifier.padding(16.dp),
                                    shape = RoundedCornerShape(8.dp),
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(8f)
                                        ) {
                                            Text(
                                                text = review.targetUsername!!,
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = Color.Gray
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = review.content!!,
                                                style = MaterialTheme.typography.labelMedium
                                            )
                                        }
                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Button(onClick = {
                                                reviewViewModel.value.delete(
                                                    review.id!!,
                                                    username = review.authorUsername!!
                                                )
                                                makeToast(
                                                    context = context,
                                                    context.resources.getString(R.string.deletedReview)
                                                )
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Delete,
                                                    contentDescription = stringResource(
                                                        id = R.string.deleteReview
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showUpdateForm.value) {
        UpdateFormActivity(userViewModel = userViewModel, showUpdateForm = showUpdateForm)
    }
}

private fun makeToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}
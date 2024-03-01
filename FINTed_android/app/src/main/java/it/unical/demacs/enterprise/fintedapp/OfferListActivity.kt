package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.material.Text
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.ReviewViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun OfferListActivity(
    context: Context,
    selectedIndex: MutableState<Index>,
    offerViewModel: MutableState<OfferViewModel>,
    userViewModel: MutableState<UserViewModel>,
    offerSheetStates: SnapshotStateMap<Long, OfferInfos>,
    postViewModel: MutableState<PostViewModel>,
    reviewViewModel: ReviewViewModel,
    coroutineScope: CoroutineScope
) {
    offerViewModel.value.getSellOffers(userViewModel.value.personalProfile.value.id!!)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            if (offerViewModel.value.offerList.value.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Default.List, contentDescription = "", modifier = Modifier.size(75.dp))
                    Text(text = stringResource(id = R.string.noOffers), style = MaterialTheme.typography.titleLarge)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(
                        items = offerViewModel.value.offerList.value,
                        key = { offer -> offer.id!! }) { offer ->

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
                                Row() {
                                    ClickableText(
                                        text = AnnotatedString(offer.postTitle!! + "[" + offer.postId + "]"),
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier.padding(horizontal = 10.dp),
                                        onClick = {
                                            offerSheetStates[offer.id!!] = OfferInfos.POST
                                        },
                                    )
                                }
                                Row(){
                                    ClickableText(text = AnnotatedString(offer.userUsername!!), onClick = { offerSheetStates[offer.id!!] = OfferInfos.USER })
                                    Text(
                                        text = "offre: \t" + offer.offer + "\t€\t",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }

                                Button(
                                    onClick = {
                                        if(offerViewModel.value.acceptOffer(offer)){
                                            makeToast(
                                                context,
                                                context.resources.getString(R.string.acceptedOffer)
                                            )
                                        }
                                    },
                                    modifier = Modifier.padding(top = 12.dp),
                                    colors = buttonColors(MaterialTheme.colorScheme.primaryContainer)
                                ) {
                                    Text(stringResource(id = R.string.acceptOffer))
                                }
                                Button(
                                    onClick = {
                                        if(offerViewModel.value.denyOffer(offer)){
                                            makeToast(
                                                context,
                                                context.resources.getString(R.string.deniedOffer)
                                            )
                                        }
                                    },
                                    modifier = Modifier.padding(top = 12.dp),
                                    colors = buttonColors(MaterialTheme.colorScheme.primaryContainer)
                                ) {
                                    Text(stringResource(id = R.string.denyOffer))
                                }
                                if (offerSheetStates[offer.id!!] != OfferInfos.NONE) {
                                    if (offerSheetStates[offer.id!!] == OfferInfos.POST) {
                                        postViewModel.value.get(offer.postId!!)

                                        Dialog(onDismissRequest = { offerSheetStates[offer.id!!] = OfferInfos.NONE }) {
                                            PostActivity(
                                                post = postViewModel.value.post.value,
                                                coroutineScope = coroutineScope,
                                                context = context,
                                                selectedIndex = selectedIndex,
                                                postType = PostType.FOR_PERSONAL_PROFILE,
                                                userViewModel = userViewModel.value,
                                                offerViewModel = offerViewModel.value,
                                                postSheetStates = null
                                            )
                                        }
                                    }
                                    else if ( offerSheetStates[offer.id!!] == OfferInfos.USER ){
                                        userViewModel.value.getUser(offer.userId!!)

                                        Dialog(onDismissRequest = { offerSheetStates[offer.id!!] = OfferInfos.NONE }) {
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

}


private fun makeToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}

package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.wear.compose.material.Text
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import it.unical.demacs.enterprise.fintedapp.models.SpeditionDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun OfferAndSpeditionActivity(
    postViewModel: MutableState<PostViewModel>,
    offerViewModel: MutableState<OfferViewModel>,
    userViewModel: MutableState<UserViewModel>
){
    val index = remember { mutableStateOf(OfferAndSpeditionActivityIndex.OFFER_LIST) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { index.value = OfferAndSpeditionActivityIndex.OFFER_LIST }) {
                Text(text = stringResource(id = R.string.yourOfferList))
            }
            Button(onClick = { index.value = OfferAndSpeditionActivityIndex.SPEDITION_LIST }) {
                Text(text = stringResource(id = R.string.yourSpeditionList))
            }
        }
        if(index.value == OfferAndSpeditionActivityIndex.OFFER_LIST){
            offerViewModel.value.getOffersOnYouPosts()

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(items = offerViewModel.value.offerList.value,
                        key = { offer: OfferDto -> offer.id!! }) { offer: OfferDto ->
                        OfferActivity(
                            offer = offer,
                            offerViewModel = offerViewModel,
                            scope = OfferActivityScope.RECV_OFFERS,
                            userViewModel = userViewModel,
                            postViewModel = postViewModel,
                        )
                    }
                }
            }
        }
        else if (index.value == OfferAndSpeditionActivityIndex.SPEDITION_LIST){
            offerViewModel.value.getPersonalSpedition()

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(items = offerViewModel.value.speditionList.value,
                        key = { spedition: SpeditionDto -> spedition.id!! }) { spedition: SpeditionDto ->
                        SpeditionActivity(spedition = spedition, scope = SpeditionActivityScope.SPEDITION_LIST)
                    }
                }
            }
        }

    }
}

enum class OfferAndSpeditionActivityIndex {
    OFFER_LIST, SPEDITION_LIST
}
package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.OfferControllerApi
import it.unical.demacs.enterprise.fintedapp.apis.SpeditionControllerApi
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import it.unical.demacs.enterprise.fintedapp.models.SpeditionDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OfferViewModel(context: Context) : ViewModel() {
    private val offerControllerApi: OfferControllerApi = OfferControllerApi(context = context)
    private val speditionControllerApi: SpeditionControllerApi = SpeditionControllerApi(context = context)

    val offerList: MutableState<List<OfferDto>> = mutableStateOf(listOf())
    val offer: MutableState<OfferDto> = mutableStateOf(OfferDto())

    val spedition: MutableState<SpeditionDto> = mutableStateOf(SpeditionDto())
    val speditionList: MutableState<List<SpeditionDto>> = mutableStateOf(listOf())

    fun save(
        postId: Long,
        userUsername: String = AuthValues.username.value,
        offerLong: Long
    ){
        CoroutineScope(Dispatchers.IO).launch {
            offer.value = offerControllerApi.save3(
                authorization = AuthValues.accessToken.value.accessToken!!,
                body = OfferDto(
                    postId = postId,
                    userUsername = userUsername,
                    offer = offerLong
                )
            )
        }
    }

    fun getPersonal(){
        CoroutineScope(Dispatchers.IO).launch {
            offerList.value = offerControllerApi.getPersonalOffers(
                username = AuthValues.username.value,
                authorization = AuthValues.accessToken.value.accessToken!!
            ).toList()
        }
    }

    fun getOffersOnYouPosts(){
        CoroutineScope(Dispatchers.IO).launch {
            offerList.value = offerControllerApi.getPersonalOffers(
                username = AuthValues.username.value,
                authorization = AuthValues.accessToken.value.accessToken!!
            ).toList()
        }
    }

    fun delete(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            offerControllerApi.delete3(
                username = AuthValues.username.value,
                authorization = AuthValues.accessToken.value.accessToken!!,
                id = id
            )
        }
    }

    fun denyOffer(offer: OfferDto){
        CoroutineScope(Dispatchers.IO).launch {
            offerControllerApi.denyOffer(
                body = offer,
                username = AuthValues.username.value,
                authorization = AuthValues.accessToken.value.accessToken!!
            )
        }
    }

    fun acceptOffer(offer: OfferDto){
        CoroutineScope(Dispatchers.IO).launch {
            spedition.value = offerControllerApi.acceptOffer(
                body = offer,
                username = AuthValues.username.value,
                authorization = AuthValues.accessToken.value.accessToken!!
            ) as SpeditionDto
        }
    }

    fun getPersonalSpedition(){
        CoroutineScope(Dispatchers.IO).launch {
            speditionList.value = speditionControllerApi.getPersonal1(
                username = AuthValues.username.value,
                authorization = AuthValues.accessToken.value.accessToken!!
            ).toList()
        }
    }
}
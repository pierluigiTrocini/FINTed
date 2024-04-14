package it.unical.demacs.enterprise.fintedapp.viewmodels

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

class OfferViewModel(): ViewModel() {
    private val offerControllerApi: OfferControllerApi = OfferControllerApi()
    private val speditionControllerApi: SpeditionControllerApi = SpeditionControllerApi()

    val offerList: MutableState<List<OfferDto>> = mutableStateOf(listOf())
    val offer: MutableState<OfferDto> = mutableStateOf(OfferDto())

    val spedition: MutableState<SpeditionDto> = mutableStateOf(SpeditionDto())
    val speditionList: MutableState<List<SpeditionDto>> = mutableStateOf(listOf())

    fun save(
        postId: Long,
        userUsername: String = AuthValues.personalProfile.username!!,
        offerLong: Long
    ){
        CoroutineScope(Dispatchers.IO).launch {
            offer.value = offerControllerApi.save3(
                authorization = AuthValues.accessTokenResponse.accessToken!!,
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
                username = AuthValues.personalProfile.username!!,
                authorization = AuthValues.accessTokenResponse.accessToken!!
            ).toList()
        }
    }

    fun getOffersOnYouPosts(){
        CoroutineScope(Dispatchers.IO).launch {
            offerList.value = offerControllerApi.getPersonalOffers(
                username = AuthValues.personalProfile.username!!,
                authorization = AuthValues.accessTokenResponse.accessToken!!
            ).toList()
        }
    }

    fun delete(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            offerControllerApi.delete3(
                username = AuthValues.personalProfile.username!!,
                authorization = AuthValues.accessTokenResponse.accessToken!!,
                id = id
            )
        }
    }

    fun denyOffer(offer: OfferDto){
        CoroutineScope(Dispatchers.IO).launch {
            offerControllerApi.denyOffer(
                body = offer,
                username = AuthValues.personalProfile.username!!,
                authorization = AuthValues.accessTokenResponse.accessToken!!
            )
        }
    }

    fun acceptOffer(offer: OfferDto){
        CoroutineScope(Dispatchers.IO).launch {
            spedition.value = offerControllerApi.acceptOffer(
                body = offer,
                username = AuthValues.personalProfile.username!!,
                authorization = AuthValues.accessTokenResponse.accessToken!!
            )
        }
    }

    fun getPersonalSpedition(){
        CoroutineScope(Dispatchers.IO).launch {
            speditionList.value = speditionControllerApi.getPersonal1(
                username = AuthValues.personalProfile.username!!,
                authorization = AuthValues.accessTokenResponse.accessToken!!
            ).toList()
        }
    }
}
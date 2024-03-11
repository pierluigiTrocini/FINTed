package it.unical.demacs.enterprise.fintedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.OfferControllerApi
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OfferViewModel(userViewModel: MutableState<UserViewModel>): ViewModel() {
    private val userViewModel = userViewModel

    private val offerApi: OfferControllerApi = OfferControllerApi()

    val offerList: MutableState<List<OfferDto>> = mutableStateOf(listOf())

    val offer: MutableState<OfferDto> = mutableStateOf(OfferDto())

    fun save(postId: Long, userId: Long, offer: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userViewModel.value.accessTokenResponse.value.access_token?.let {
                offerApi.save3(
                    OfferDto(
                        postId = postId,
                        userId = userId,
                        offer =  offer.toLong(),
                        userUsername = userViewModel.value.personalProfile.value.username
                    ),
                    token = it
                )
            }
        }
    }

    fun delete(offerId: Long, username: String){
        CoroutineScope(Dispatchers.IO).launch {
            userViewModel.value.accessTokenResponse.value.access_token?.let { offerApi.delete3(offerId, username, token = it) }
        }
    }

    fun getPostOffers(postId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            offerList.value = userViewModel.value.accessTokenResponse.value.access_token?.let {
                offerApi.getPostOffers(postId,
                    it
                ).toList()
            }!!
        }
    }

    fun getUserOffers(userId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            offerList.value = userViewModel.value.accessTokenResponse.value.access_token?.let { offerApi.getUserOffers(userId, token = it).toList() }!!
        }
    }

    fun getSellOffers(userId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            offerList.value = userViewModel.value.accessTokenResponse.value.access_token?.let { offerApi.getSellOffers(userId, token = it).toList() }!!
        }
    }

    fun acceptOffer(offerDto: OfferDto): Boolean{
        var accepted = false;
        CoroutineScope(Dispatchers.IO).launch {
            offer.value = userViewModel.value.accessTokenResponse.value.access_token?.let { offerApi.acceptOffer(offerDto, token = it) }!!
            if(offer.value.offerStatus == OfferDto.OfferStatus.ACCEPTED){
                accepted = true
            }
        }

        return accepted
    }

    fun denyOffer(offerDto: OfferDto): Boolean{
        var denied = false;
        CoroutineScope(Dispatchers.IO).launch {
            offer.value = userViewModel.value.accessTokenResponse.value.access_token?.let { offerApi.denyOffer(offerDto, token = it) }!!
            if(offer.value.offerStatus == OfferDto.OfferStatus.DENIED){
                denied = true
            }
        }

        return denied
    }

}
package it.unical.demacs.enterprise.fintedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.OfferControllerApi
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.models.UserDto

class OfferViewModel: ViewModel() {
    private val offerApi: OfferControllerApi = OfferControllerApi()

    val offerList: MutableState<List<OfferDto>> = mutableStateOf(listOf())

    fun save(postId: Long, userId: Long, offer: Long ): OfferDto {
        return offerApi.save3(
            OfferDto(
                post = PostDto(id = postId),
                user = UserDto(id = userId),
                offer = offer
            )
        )
    }

    fun delete(offerId: Long){
        offerApi.delete3(offerId)
    }

    fun getPostOffers(postId: Long){
        offerList.value = offerApi.getPostOffers(postId).toList()
    }

    fun getUserOffers(userId: Long){
        offerList.value = offerApi.getUserOffers(userId).toList()
    }

}
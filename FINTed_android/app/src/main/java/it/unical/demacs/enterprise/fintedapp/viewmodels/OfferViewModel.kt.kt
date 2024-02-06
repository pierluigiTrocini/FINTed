package it.unical.demacs.enterprise.fintedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.OfferControllerApi
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OfferViewModel: ViewModel() {
    private val offerApi: OfferControllerApi = OfferControllerApi()

    val offerList: MutableState<List<OfferDto>> = mutableStateOf(listOf())

    fun save(postId: Long, userId: Long, offer: String) {
        CoroutineScope(Dispatchers.IO).launch {
            offerApi.save3(
                OfferDto(
                    postId = postId,
                    userId = userId,
                    offer =  offer.toLong()
                )
            )
        }
    }

    fun delete(offerId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            offerApi.delete3(offerId)
        }
    }

    fun getPostOffers(postId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            offerList.value = offerApi.getPostOffers(postId).toList()
        }
    }

    fun getUserOffers(userId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            offerList.value = offerApi.getUserOffers(userId).toList()
        }
    }

}
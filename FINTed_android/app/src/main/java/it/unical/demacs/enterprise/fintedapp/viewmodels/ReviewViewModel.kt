package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.ReviewControllerApi
import it.unical.demacs.enterprise.fintedapp.models.ReviewDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviewViewModel(context: Context) : ViewModel() {
    private val reviewControllerApi: ReviewControllerApi = ReviewControllerApi(context = context)

    val reviewList: MutableState<List<ReviewDto>> = mutableStateOf(listOf())
    val review: MutableState<ReviewDto> = mutableStateOf(ReviewDto())

    fun save(
        targetUsername: String,
        content: String
    ){
        CoroutineScope(Dispatchers.IO).launch {
            review.value = reviewControllerApi.save1(
                authorization = AuthValues.accessToken.value.accessToken!!,
                body = ReviewDto(
                    authorUsername = AuthValues.username.value,
                    targetUsername = targetUsername,
                    content = content
                )
            )
        }
    }

    fun getPersonal(){
        CoroutineScope(Dispatchers.IO).launch {
            reviewList.value = reviewControllerApi.getPersonal2(
                authorization = AuthValues.accessToken.value.accessToken!!,
                username = AuthValues.username.value
            ).toList()
        }
    }

    fun getByTarget(username: String){
        CoroutineScope(Dispatchers.IO).launch {
            reviewList.value = reviewControllerApi.getByTarget(
                username = username
            ).toList()
        }
    }

    fun delete(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            reviewControllerApi.delete1(
                username = AuthValues.username.value,
                id = id,
                authorization = AuthValues.accessToken.value.accessToken!!
            )
        }
    }

}
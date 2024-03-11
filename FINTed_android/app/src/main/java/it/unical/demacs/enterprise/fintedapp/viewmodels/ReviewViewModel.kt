package it.unical.demacs.enterprise.fintedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.ReviewControllerApi
import it.unical.demacs.enterprise.fintedapp.models.ReviewDto
import it.unical.demacs.enterprise.fintedapp.models.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

data class ReviewState (
    val id: Long = 0,
    val authorId: Long = 0,
    val targetId: Long = 0,
    val publishDate: LocalDateTime? = null,
    val content: String = ""
)

class ReviewViewModel(userViewModel: MutableState<UserViewModel>): ViewModel() {
    private val userViewModel = userViewModel

    private val _reviewState = MutableStateFlow(ReviewState())
    val reviewState: StateFlow<ReviewState> = _reviewState.asStateFlow()
    val reviewList: MutableState<List<ReviewDto>> = mutableStateOf(listOf())

    private val reviewApi: ReviewControllerApi = ReviewControllerApi()

    fun publish() {
        CoroutineScope(Dispatchers.IO).launch {
            userViewModel.value.accessTokenResponse.value.access_token?.let {
                reviewApi.save1(
                    ReviewDto(
                        authorId = reviewState.value.authorId,
                        targetId = reviewState.value.targetId,
                        authorUsername = userViewModel.value.personalProfile.value.username,
                        content = reviewState.value.content,
                        publishDate = null
                    ),
                    token = it
                )
            }
        }
    }

    fun getByAuthor(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            reviewList.value = userViewModel.value.accessTokenResponse.value.access_token?.let { reviewApi.getAuthorReviews(id, token = it).toList() }!!
        }
    }

    fun getByTarget(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            reviewList.value = userViewModel.value.accessTokenResponse.value.access_token?.let { reviewApi.getTargetReviews(id, token = it).toList() }!!
        }
    }

    fun delete(id: Long, username: String){
        CoroutineScope(Dispatchers.IO).launch {
            userViewModel.value.accessTokenResponse.value.access_token?.let { reviewApi.delete1(id, username = username, token = it) }
        }
    }
}
package it.unical.demacs.enterprise.fintedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.ReviewControllerApi
import it.unical.demacs.enterprise.fintedapp.models.ReviewDto
import it.unical.demacs.enterprise.fintedapp.models.UserDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime

data class ReviewState (
    val id: Long = 0,
    val authorId: Long = 0,
    val targetId: Long = 0,
    val publishDate: LocalDateTime? = null,
    val content: String = ""
)

class ReviewViewModel: ViewModel() {
    private val _reviewState = MutableStateFlow(ReviewState())
    val reviewState: StateFlow<ReviewState> = _reviewState.asStateFlow()
    val reviewList: MutableState<List<ReviewDto>> = mutableStateOf(listOf())

    private val reviewApi: ReviewControllerApi = ReviewControllerApi()

    fun publish(): ReviewDto {
        return reviewApi.save1(
            ReviewDto(
                author = UserDto(id = reviewState.value.authorId),
                target = UserDto(id = reviewState.value.targetId),
                content = reviewState.value.content,
                publishDate = null
            )
        )
    }

    fun getByAuthor(id: Long) {
        reviewList.value = reviewApi.getAuthorReviews(id).toList()
    }

    fun getByTarget(id: Long){
        reviewList.value = reviewApi.getTargetReviews(id).toList()
    }

    fun delete(id: Long){
        reviewApi.delete1(id)
    }
}
package it.unical.demacs.enterprise.fintedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.PostControllerApi
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.models.UserDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime

data class PostState(
    val id: Long = 0,
    val sellerId: Long = 0,
    val title: String = "",
    val startingPrice: Long = 0,
    val postImage: String = "",
    val publishDate: LocalDateTime? = null,
)

class PostViewModel : ViewModel() {
    private val _postPublishState = MutableStateFlow(PostState())
    val postPublishState: StateFlow<PostState> = _postPublishState.asStateFlow()

    val postList: MutableState<List<PostDto>> = mutableStateOf(listOf())

    private val postApi: PostControllerApi = PostControllerApi()

    fun publish(): PostDto {
        return postApi.save2(
            PostDto(
                seller = UserDto(id = postPublishState.value.sellerId),
                title = postPublishState.value.title,
                startingPrice = postPublishState.value.startingPrice,
                postImage = postPublishState.value.postImage
            )
        )
    }

    fun delete(id: Long){
        postApi.delete2(id)
    }

    fun getAll(page: Int){
        postList.value = postApi.getAll1(page).toList()
    }

    fun get(id: Long): PostDto {
        return postApi.get1(id);
    }
}
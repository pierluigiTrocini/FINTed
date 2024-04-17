package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.PostControllerApi
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(context: Context) : ViewModel() {
    private val postControllerApi: PostControllerApi = PostControllerApi(context = context)

    val postList: MutableState<List<PostDto>> = mutableStateOf(listOf())
    val post: MutableState<PostDto> = mutableStateOf(PostDto())

    fun save(
        title: String,
        startingPrice: Long,
        postImage: String
    ){
        CoroutineScope(Dispatchers.IO).launch {
            postControllerApi.save2(
                PostDto(
                    title = title,
                    startingPrice = startingPrice,
                    postImage = postImage,
                    sellerUsername = AuthValues.personalProfile.username!!,
                    sellerId = AuthValues.personalProfile.id!!
                ),
                authorization = AuthValues.accessTokenResponse.accessToken!!
            )
        }
    }

    fun get(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            post.value = postControllerApi.get(postId = id)
        }
    }

    fun getByUser(username: String){
        CoroutineScope(Dispatchers.IO).launch {
            postList.value = postControllerApi.getByUser(username).toList()
        }
    }

    fun search(content: String) {
        CoroutineScope(Dispatchers.IO).launch {
            postList.value = postControllerApi.searchByTitle(content = content).toList()
        }
    }

    fun getAll(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            postList.value = postControllerApi.getAll1(page = page).toList()
        }
    }

    fun delete(username: String, id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            postControllerApi.delete2(postId = id, username = username, authorization = AuthValues.accessTokenResponse.accessToken!!)
        }
    }

}
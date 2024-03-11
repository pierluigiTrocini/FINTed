package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.PostControllerApi
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.models.UserDto
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.util.Base64

data class PostState(
    val id: Long = 0,
    val sellerId: Long = 0,
    var title: String = "",
    var startingPrice: String = "",
    var postImage: String = "",
    val publishDate: LocalDateTime? = null,
)

class PostViewModel(userViewModel: MutableState<UserViewModel>) : ViewModel() {
    private val userViewModel = userViewModel

    val postPublishState = MutableStateFlow(PostState())

    val postList: MutableState<List<PostDto>> = mutableStateOf(listOf())
    
    val post: MutableState<PostDto> = mutableStateOf(PostDto())

    private val postApi: PostControllerApi = PostControllerApi()

    fun delete(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            userViewModel.value.accessTokenResponse.value.access_token?.let { postApi.delete2(id, token = it) }
        }
    }

    fun getAll(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            postList.value =
                userViewModel.value.accessTokenResponse.value.access_token?.let { postApi.getAll1(page, token = it).toList() }!!
        }
    }

    fun getHomepage(page: Int, userId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            postList.value = userViewModel.value.accessTokenResponse.value.access_token?.let { postApi.getHomepage(page = page, user = userId, token = it).toList() }!!
        }
    }

    fun get(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            post.value =
                userViewModel.value.accessTokenResponse.value.access_token?.let { postApi.get1(id, token = it) }!!;
        }
    }
    fun publishPost(
        title: String,
        startingPrice: String,
        postImage: String,
        seller: UserPersonalProfileDto
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            userViewModel.value.accessTokenResponse.value.access_token?.let {
                postApi.save2(
                    PostDto(
                        title = title,
                        startingPrice = startingPrice.toLong(),
                        postImage = postImage,
                        sellerId = seller.id,
                        sellerUsername = userViewModel.value.personalProfile.value.username
                    ),
                    token = it
                )
            }
        }
    }
    fun updateImage(result: ActivityResult, context: Context): String {
        var content = "";

        val uri = result.data?.data
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val byteArrayOutputStream = ByteArrayOutputStream()

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            postPublishState.value.postImage = Base64.getEncoder().encodeToString(byteArray)
            content = Base64.getEncoder().encodeToString(byteArray)
        }

        return content
    }

    fun decodeImageToBitmap(value: String): ImageBitmap {
        val byteArray = Base64.getDecoder().decode(value)
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

        return bitmap.asImageBitmap()
    }

}
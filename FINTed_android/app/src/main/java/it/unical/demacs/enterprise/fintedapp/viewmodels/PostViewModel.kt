package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.PostControllerApi
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.Base64

class PostViewModel(context: Context) : ViewModel() {
    private val postControllerApi: PostControllerApi = PostControllerApi(context = context)

    val postList: MutableState<List<PostDto>> = mutableStateOf(listOf())
    val post: MutableState<PostDto> = mutableStateOf(PostDto())

    fun convertImageToBase64(imageUri: Uri, context: Context): String {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.getEncoder().encodeToString(byteArray)
    }

    fun save(
        title: String,
        startingPrice: Long,
        postImage: String
    ){
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("PIERLUIGI", title + "\t" + startingPrice + "\t" + postImage)
            Log.d("PIERLUIGI", AuthValues.accessToken.value.accessToken!!)

            postControllerApi.save2(
                PostDto(
                    title = title,
                    startingPrice = startingPrice,
                    postImage = postImage,
                    sellerUsername = AuthValues.username.value
                ),
                authorization = AuthValues.accessToken.value.accessToken!!
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
            postControllerApi.delete2(postId = id, username = username, authorization = AuthValues.accessToken.value.accessToken!!)
        }
    }
}
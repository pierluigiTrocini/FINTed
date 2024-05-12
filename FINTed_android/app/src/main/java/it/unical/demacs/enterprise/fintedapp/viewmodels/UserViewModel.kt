package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.CurrentIndex
import it.unical.demacs.enterprise.fintedapp.apis.AuthControllerApi
import it.unical.demacs.enterprise.fintedapp.apis.UserControllerApi
import it.unical.demacs.enterprise.fintedapp.models.UserDto
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserRegistrationDto
import it.unical.demacs.enterprise.fintedapp.ui.utility.AppIndex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {
    private val userControllerApi: UserControllerApi = UserControllerApi(context = context)
    private val authControllerApi: AuthControllerApi = AuthControllerApi(context = context)

    val userList: MutableState<List<UserDto>> = mutableStateOf(listOf())

    val personalProfile: MutableState<UserPersonalProfileDto> = mutableStateOf(UserPersonalProfileDto())
    val basicUser: MutableState<UserProfileDto> = mutableStateOf(UserProfileDto())

    fun registration(
        firstName: String,
        lastName: String,
        username: String,
        credentialsEmail: String,
        credentialsPassword: String,
        addressCity: String,
        addressNumber: String,
        addressRoute: String
    ){
        CoroutineScope(Dispatchers.IO).launch {
            AuthValues.accessToken.value = userControllerApi.save(
                UserRegistrationDto(
                    id = null,
                    firstName = firstName,
                    lastName = lastName,
                    username = username,
                    credentialsEmail = credentialsEmail,
                    credentialsPassword = credentialsPassword,
                    addressRoute = addressRoute,
                    addressNumber = addressNumber,
                    addressCity = addressCity
                ))

            AuthValues.username.value = username

            CurrentIndex.appIndex.value = AppIndex.HOMEPAGE
        }
    }

    fun get(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            basicUser.value = userControllerApi.get(
                username = username,
                authorization = AuthValues.accessToken.value.accessToken!!
            )
        }
    }

    fun getPersonal(username: String){
        CoroutineScope(Dispatchers.IO).launch {
            personalProfile.value = userControllerApi.getPersonal(
                username = username,
                authorization = AuthValues.accessToken.value.accessToken!!
            )
        }
    }

    fun getAll(page: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userList.value = userControllerApi.getAll(page = page).toList()
        }
    }

    fun search(content: String){
        CoroutineScope(Dispatchers.IO).launch {
            userList.value = userControllerApi.searchUser(content = content).toList()
        }
    }

    fun delete(username: String){
        CoroutineScope(Dispatchers.IO).launch {
            userControllerApi.delete(username = username, authorization = AuthValues.accessToken.value.accessToken!!)
        }
    }
}
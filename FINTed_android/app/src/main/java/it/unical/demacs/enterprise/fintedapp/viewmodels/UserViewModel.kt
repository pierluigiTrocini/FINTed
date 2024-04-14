package it.unical.demacs.enterprise.fintedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.UserControllerApi
import it.unical.demacs.enterprise.fintedapp.models.UserDto
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserRegistrationDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel() : ViewModel() {
    private val userControllerApi: UserControllerApi = UserControllerApi()

    val personalProfile: MutableState<UserPersonalProfileDto> = mutableStateOf(UserPersonalProfileDto())
    val userList: MutableState<List<UserDto>> = mutableStateOf(listOf())

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
            AccessToken.accessTokenResponse = userControllerApi.save(
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
                )
            )
        }
    }

    fun getPersonal(username: String){
        CoroutineScope(Dispatchers.IO).launch {
            personalProfile.value = AccessToken.accessTokenResponse.accessToken?.let {
                userControllerApi.getPersonal(
                    username = username,
                    authorization = it
                )
            }!!
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
            AccessToken.accessTokenResponse.accessToken?.let { userControllerApi.delete(username = username, authorization = it) }
        }
    }
}
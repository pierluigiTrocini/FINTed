package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.KeycloakControllerApi
import it.unical.demacs.enterprise.fintedapp.apis.UserControllerApi
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserRegistrationDto
import it.unical.demacs.enterprise.fintedapp.models.security.AccessTokenResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel() : ViewModel() {
    private val keycloakControllerApi: KeycloakControllerApi = KeycloakControllerApi()

    val accessTokenResponse: MutableState<AccessTokenResponse> =
        mutableStateOf(AccessTokenResponse())
    val logged = mutableStateOf(false)

    private val userApi: UserControllerApi = UserControllerApi()

    val userProfileList: MutableState<List<UserProfileDto>> = mutableStateOf(listOf())
    val personalProfile: MutableState<UserPersonalProfileDto> =
        mutableStateOf(UserPersonalProfileDto())

    val basicUser: MutableState<UserProfileDto> = mutableStateOf(UserProfileDto())

    fun logout(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            accessTokenResponse.value.access_token?.let {
                personalProfile.value.username?.let { it1 ->
                    keycloakControllerApi.logout(
                        accessToken = it,
                        username = it1
                    )
                }
                logged.value = false
            }
        }
    }

    fun login(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            accessTokenResponse.value = keycloakControllerApi.login(username, password)
            logged.value = true
            personalProfile.value = userApi.getPersonalProfile(
                username = username,
                token = accessTokenResponse.value.access_token!!
            )
        }
    }

    fun registration(
        firstName: String,
        lastName: String,
        username: String,
        credentialsEmail: String,
        credentialsPassword: String,
        addressCity: String,
        addressNumber: String,
        addressRoute: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            accessTokenResponse.value = userApi.save(
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
            logged.value = true
            personalProfile.value = userApi.getPersonalProfile(
                username = username,
                token = accessTokenResponse.value.access_token!!
            )
        }
    }

    fun getPersonalProfile(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            personalProfile.value = accessTokenResponse.value.access_token?.let {
                userApi.getPersonalProfile(
                    username,
                    token = it
                )
            }!!
        }
    }

    fun getUser(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            basicUser.value =
                accessTokenResponse.value.access_token?.let {
                    userApi.get(
                        username,
                        token = it
                    )
                }!!;
        }
    }

    fun getUserList(page: Int) {
        userProfileList.value =
            accessTokenResponse.value.access_token?.let {
                userApi.getAll(
                    page,
                    token = it
                ).toList()
            }!!
    }

    fun updatePersonalProfile(userDto: UserPersonalProfileDto) {
        CoroutineScope(Dispatchers.IO).launch {
            accessTokenResponse.value.access_token?.let { userApi.update(userDto, token = it) }
            personalProfile.value = userDto.username?.let {
                userApi.getPersonalProfile(
                    username = it,
                    token = accessTokenResponse.value.access_token!!
                )
            }!!
        }
    }

}
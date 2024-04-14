package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.AuthControllerApi
import it.unical.demacs.enterprise.fintedapp.apis.UserControllerApi
import it.unical.demacs.enterprise.fintedapp.models.AccessTokenResponse
import it.unical.demacs.enterprise.fintedapp.models.Credentials
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel(){
    private val authControllerApi: AuthControllerApi = AuthControllerApi()
    private val userControllerApi: UserControllerApi = UserControllerApi()

    fun login(credentials: Credentials, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            AuthValues.accessTokenResponse = authControllerApi.login(Credentials())
            AuthValues.personalProfile = userControllerApi.getPersonal(
                authorization = AuthValues.accessTokenResponse.accessToken!!,
                username = credentials.username!!)
        }
    }

    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            AuthValues.accessTokenResponse.accessToken?.let { authControllerApi.logout(it) }

            AuthValues.personalProfile = UserPersonalProfileDto()
            AuthValues.accessTokenResponse = AccessTokenResponse()
        }
    }

}
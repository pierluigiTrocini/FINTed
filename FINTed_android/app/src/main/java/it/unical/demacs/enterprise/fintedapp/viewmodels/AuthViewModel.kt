package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.AuthControllerApi
import it.unical.demacs.enterprise.fintedapp.apis.UserControllerApi
import it.unical.demacs.enterprise.fintedapp.models.AccessTokenResponse
import it.unical.demacs.enterprise.fintedapp.models.Credentials
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(context: Context) : ViewModel() {
    private val authControllerApi: AuthControllerApi = AuthControllerApi(context = context)

    fun login(username: String, password: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            AuthValues.accessToken.value = authControllerApi.login(
                Credentials(
                    username = username,
                    password = password
                )
            )
            AuthValues.username.value = username

            Log.d("PIERLUIGI", AuthValues.accessToken.value.accessToken!!)
        }
    }

    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            authControllerApi.logout(
                AuthValues.accessToken.value.accessToken!!
            )

            AuthValues.username.value = ""
            AuthValues.accessToken.value = AccessTokenResponse()
        }
    }

}
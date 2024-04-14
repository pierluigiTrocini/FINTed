package it.unical.demacs.enterprise.fintedapp.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.AuthControllerApi
import it.unical.demacs.enterprise.fintedapp.models.AccessTokenResponse
import it.unical.demacs.enterprise.fintedapp.models.Credentials
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel(){
    private val authControllerApi: AuthControllerApi = AuthControllerApi()

    fun login(credentials: Credentials, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            AccessToken.accessTokenResponse = authControllerApi.login(Credentials())
        }
    }

    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            AccessToken.accessTokenResponse.accessToken?.let { authControllerApi.logout(it) }
        }
    }

}
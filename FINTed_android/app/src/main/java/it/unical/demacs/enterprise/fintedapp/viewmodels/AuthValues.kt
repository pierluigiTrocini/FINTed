package it.unical.demacs.enterprise.fintedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import it.unical.demacs.enterprise.fintedapp.models.AccessTokenResponse
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto

class AuthValues {
    companion object{
        var accessToken: MutableState<AccessTokenResponse> = mutableStateOf(AccessTokenResponse())
        var username: MutableState<String> = mutableStateOf("")
    }
}
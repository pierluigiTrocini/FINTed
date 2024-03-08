package it.unical.demacs.enterprise.fintedapp.apis

import android.media.session.MediaSession.Token
import it.unical.demacs.enterprise.fintedapp.infrastructure.ApiClient
import it.unical.demacs.enterprise.fintedapp.infrastructure.ClientError
import it.unical.demacs.enterprise.fintedapp.infrastructure.ClientException
import it.unical.demacs.enterprise.fintedapp.infrastructure.RequestConfig
import it.unical.demacs.enterprise.fintedapp.infrastructure.RequestMethod
import it.unical.demacs.enterprise.fintedapp.infrastructure.ResponseType
import it.unical.demacs.enterprise.fintedapp.infrastructure.ServerError
import it.unical.demacs.enterprise.fintedapp.infrastructure.ServerException
import it.unical.demacs.enterprise.fintedapp.infrastructure.Success
import it.unical.demacs.enterprise.fintedapp.models.security.AccessTokenRequest
import it.unical.demacs.enterprise.fintedapp.models.security.TokenResponse
import java.net.URLEncoder

class AuthController(basePath: String = ApiResources().authUrl): ApiClient(basePath) {

    @Suppress("UNCHECKED_CAST")
    fun getAccessToken(username: String, password: String): TokenResponse {
        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            ApiResources().authPath
        )

        val accessTokenRequest = mapOf(
            "grant_type" to ApiResources().grant_type_password,
            "client_id" to ApiResources().client_id,
            "username" to username,
            "password" to password
        )

        val response = request<Any?>(
            localVariableConfig,
            accessTokenRequest.entries.joinToString("&") { "${it.key}=${URLEncoder.encode(it.value, "UTF-8")}" }
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as TokenResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun getRefreshToken(refreshToken: String): TokenResponse {
        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            ApiResources().authPath
        )

        val accessTokenRequest = mapOf(
            "grant_type" to ApiResources().grant_type_password,
            "client_id" to ApiResources().client_id,
            "refresh_token" to refreshToken
        )

        val response = request<Any?>(
            localVariableConfig,
            accessTokenRequest.entries.joinToString("&") { "${it.key}=${URLEncoder.encode(it.value, "UTF-8")}" }
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as TokenResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

}
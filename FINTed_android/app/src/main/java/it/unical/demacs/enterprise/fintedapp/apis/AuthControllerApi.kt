/**
 * OpenAPI definition
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package it.unical.demacs.enterprise.fintedapp.apis

import it.unical.demacs.enterprise.fintedapp.models.AccessTokenResponse
import it.unical.demacs.enterprise.fintedapp.models.Credentials
import it.unical.demacs.enterprise.fintedapp.models.ServiceError

import it.unical.demacs.enterprise.fintedapp.infrastructure.*

class AuthControllerApi(basePath: kotlin.String = "http://localhost:8080") : ApiClient(basePath) {

    /**
     * 
     * 
     * @param body  
     * @return AccessTokenResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun login(body: Credentials): AccessTokenResponse {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/auth/login"
        )
        val response = request<AccessTokenResponse>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessTokenResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param authorization  
     * @return void
     */
    fun logout(authorization: kotlin.String): Unit {
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/auth/logout", headers = localVariableHeaders
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
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

import it.unical.demacs.enterprise.fintedapp.models.security.AccessTokenResponse
import it.unical.demacs.enterprise.fintedapp.models.ServiceError

import it.unical.demacs.enterprise.fintedapp.infrastructure.*
import it.unical.demacs.enterprise.fintedapp.models.security.KeycloakLoginBody

class KeycloakControllerApi(basePath: kotlin.String = ApiResources().backendUrl) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param username  
     * @param password  
     * @return AccessTokenResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun login(username: kotlin.String, password: kotlin.String): AccessTokenResponse {
        val localVariableBody: kotlin.Any? = KeycloakLoginBody(username, password)
        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/keycloak/login"
        )
        val response = request<AccessTokenResponse>(
            localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccessTokenResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> TODO()
            ResponseType.ServerError -> TODO()
        }
    }
    /**
     * 
     * 
     * @param accessToken  
     * @return void
     */
    fun logout(username: String, accessToken: kotlin.String): Unit {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("username", listOf(username.toString()))
            put("accessToken", listOf(accessToken.toString()))
        }
        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/keycloak/logout", query = localVariableQuery
        )
        val response = request<Any?>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> TODO()
            ResponseType.ServerError -> TODO()
        }
    }
}

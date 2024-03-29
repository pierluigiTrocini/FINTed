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

import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserRegistrationDto

import it.unical.demacs.enterprise.fintedapp.infrastructure.*
import it.unical.demacs.enterprise.fintedapp.models.security.AccessTokenResponse
import okhttp3.Headers
import okhttp3.internal.addHeaderLenient

class UserControllerApi(basePath: kotlin.String = ApiResources().backendUrl) : ApiClient(basePath) {
    /**
     *
     *
     * @param username
     * @return void
     */
    fun delete(username: kotlin.String, token: String): Unit {
        val localVariableConfig = RequestConfig(
            RequestMethod.DELETE,
            "/users/{username}".replace("{" + "username" + "}", "$username")
        )

        localVariableConfig.headers = mapOf(
            "Authorization" to "Bearer $token"
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
    /**
     *
     *
     * @param username
     * @return UserProfileDto
     */
    @Suppress("UNCHECKED_CAST")
    fun get(username: kotlin.String, token: String): UserProfileDto {
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/users/{username}".replace("{" + "username" + "}", "$username")
        )

        localVariableConfig.headers = mapOf(
            "Authorization" to "Bearer $token"
        )

        val response = request<UserProfileDto>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserProfileDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> TODO()
            ResponseType.ServerError -> TODO()
        }
    }
    /**
     *
     *
     * @param page
     * @return kotlin.Array<UserProfileDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun getAll(page: kotlin.Int, token: String): kotlin.Array<UserProfileDto> {
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/users/all/{page}".replace("{" + "page" + "}", "$page")
        )

        localVariableConfig.headers = mapOf(
            "Authorization" to "Bearer $token"
        )

        val response = request<kotlin.Array<UserProfileDto>>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<UserProfileDto>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> TODO()
            ResponseType.ServerError -> TODO()
        }
    }
    /**
     *
     *
     * @param username
     * @return UserPersonalProfileDto
     */
    @Suppress("UNCHECKED_CAST")
    fun getPersonalProfile(username: kotlin.String, token: String): UserPersonalProfileDto {
        var localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/users/personal/{username}".replace("{" + "username" + "}", "$username")
        )

        localVariableConfig.headers = mapOf(
            "Authorization" to "Bearer $token"
        )


        val response = request<UserPersonalProfileDto>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserPersonalProfileDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> TODO()
            ResponseType.ServerError -> TODO()
        }
    }
    /**
     *
     *
     * @param body
     * @return AccessTokenResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun save(body: UserRegistrationDto): AccessTokenResponse {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/users/register"
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
     * @param body
     * @return UserPersonalProfileDto
     */
    @Suppress("UNCHECKED_CAST")
    fun update(body: UserPersonalProfileDto, token: String): UserPersonalProfileDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
            RequestMethod.PUT,
            "/users/"
        )

        localVariableConfig.headers = mapOf(
            "Authorization" to "Bearer $token"
        )

        val response = request<UserPersonalProfileDto>(
            localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserPersonalProfileDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> TODO()
            ResponseType.ServerError -> TODO()
        }
    }
}

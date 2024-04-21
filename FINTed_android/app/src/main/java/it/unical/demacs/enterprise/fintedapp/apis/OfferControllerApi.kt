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

import android.content.Context
import it.unical.demacs.enterprise.fintedapp.models.OfferDto
import it.unical.demacs.enterprise.fintedapp.models.SpeditionDto

import it.unical.demacs.enterprise.fintedapp.infrastructure.*

class OfferControllerApi(basePath: String = ApiUrl.url, context: Context) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param body  
     * @param authorization  
     * @param username  
     * @return SpeditionDto
     */
    @Suppress("UNCHECKED_CAST")
    fun acceptOffer(body: OfferDto, authorization: kotlin.String, username: kotlin.String): SpeditionDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/offers/accept/{username}".replace("{" + "username" + "}", "$username"), headers = localVariableHeaders
        )
        val response = request<SpeditionDto>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as SpeditionDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param username  
     * @param id  
     * @param authorization  
     * @return void
     */
    fun delete3(username: kotlin.String, id: kotlin.Long, authorization: kotlin.String): Unit {
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/offers/{username}/{id}".replace("{" + "username" + "}", "$username").replace("{" + "id" + "}", "$id"), headers = localVariableHeaders
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
    /**
     * 
     * 
     * @param body  
     * @param authorization  
     * @param username  
     * @return void
     */
    fun denyOffer(body: OfferDto, authorization: kotlin.String, username: kotlin.String): Unit {
        val localVariableBody: kotlin.Any? = body
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/offers/deny/{username}".replace("{" + "username" + "}", "$username"), headers = localVariableHeaders
        )
        val response = request<Any?>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param username  
     * @param authorization  
     * @return kotlin.Array<OfferDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun getPersonalOffers(username: kotlin.String, authorization: kotlin.String): kotlin.Array<OfferDto> {
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/offers/personal/{username}".replace("{" + "username" + "}", "$username"), headers = localVariableHeaders
        )
        val response = request<kotlin.Array<OfferDto>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<OfferDto>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param username  
     * @param authorization  
     * @return kotlin.Array<OfferDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun getSellsOffers(username: kotlin.String, authorization: kotlin.String): kotlin.Array<OfferDto> {
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/offers/your-posts/{username}".replace("{" + "username" + "}", "$username"), headers = localVariableHeaders
        )
        val response = request<kotlin.Array<OfferDto>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<OfferDto>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @param authorization  
     * @return OfferDto
     */
    @Suppress("UNCHECKED_CAST")
    fun save3(body: OfferDto, authorization: kotlin.String): OfferDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/offers/", headers = localVariableHeaders
        )
        val response = request<OfferDto>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as OfferDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}

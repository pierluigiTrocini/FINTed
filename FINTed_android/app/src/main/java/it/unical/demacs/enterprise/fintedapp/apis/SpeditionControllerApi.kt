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
import android.widget.Toast
import it.unical.demacs.enterprise.fintedapp.models.SpeditionDto

import it.unical.demacs.enterprise.fintedapp.infrastructure.*
import it.unical.demacs.enterprise.fintedapp.models.AccessTokenResponse

class SpeditionControllerApi(basePath: String = "http://localhost:8080", context: Context) : ApiClient(basePath) {

    val context = context

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    /**
     * 
     * 
     * @param username  
     * @param authorization  
     * @return kotlin.Array<SpeditionDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun getPersonal1(username: kotlin.String, authorization: kotlin.String): kotlin.Array<SpeditionDto> {
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/speditions/personal/{username}".replace("{" + "username" + "}", "$username"), headers = localVariableHeaders
        )
        val response = request<kotlin.Array<SpeditionDto>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<SpeditionDto>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> {
                showMessage("Client error: ${(response as ClientError<*>).body as? String ?: "Client error"}")
                (response as Success<*>).data as Array<SpeditionDto>
            }
            ResponseType.ServerError -> {
                showMessage("Server error: ${(response as ClientError<*>).body as? String ?: "Server error"}")
                (response as Success<*>).data as Array<SpeditionDto>
            }
        }
    }
}

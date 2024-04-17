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
import it.unical.demacs.enterprise.fintedapp.models.PostDto

import it.unical.demacs.enterprise.fintedapp.infrastructure.*
import it.unical.demacs.enterprise.fintedapp.models.AccessTokenResponse

class PostControllerApi(basePath: String = "http://localhost:8080", context: Context) : ApiClient(basePath) {

    val context = context

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    /**
     * 
     * 
     * @param postId  
     * @param username  
     * @param authorization  
     * @return void
     */
    fun delete2(postId: kotlin.Long, username: kotlin.String, authorization: kotlin.String): Unit {
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/posts/{username}/{postId}".replace("{" + "postId" + "}", "$postId").replace("{" + "username" + "}", "$username"), headers = localVariableHeaders
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> {
                showMessage("Client error: ${(response as ClientError<*>).body as? String ?: "Client error"}")
            }
            ResponseType.ServerError -> {
                showMessage("Server error: ${(response as ClientError<*>).body as? String ?: "Server error"}")
            }
        }
    }
    /**
     * 
     * 
     * @param postId  
     * @return PostDto
     */
    @Suppress("UNCHECKED_CAST")
    fun get(postId: kotlin.Long): PostDto {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/posts/{postId}".replace("{" + "postId" + "}", "$postId")
        )
        val response = request<PostDto>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PostDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> {
                showMessage("Client error: ${(response as ClientError<*>).body as? String ?: "Client error"}")
                (response as Success<*>).data as PostDto
            }
            ResponseType.ServerError -> {
                showMessage("Server error: ${(response as ClientError<*>).body as? String ?: "Server error"}")
                (response as Success<*>).data as PostDto
            }
        }
    }
    /**
     * 
     * 
     * @param page  
     * @return kotlin.Array<PostDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun getAll1(page: kotlin.Int): kotlin.Array<PostDto> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/posts/all/{page}".replace("{" + "page" + "}", "$page")
        )
        val response = request<kotlin.Array<PostDto>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<PostDto>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> {
                showMessage("Client error: ${(response as ClientError<*>).body as? String ?: "Client error"}")
                (response as Success<*>).data as Array<PostDto>
            }
            ResponseType.ServerError -> {
                showMessage("Server error: ${(response as ClientError<*>).body as? String ?: "Server error"}")
                (response as Success<*>).data as Array<PostDto>
            }
        }
    }
    /**
     * 
     * 
     * @param username  
     * @return kotlin.Array<PostDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun getByUser(username: kotlin.String): kotlin.Array<PostDto> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/posts/user/{username}".replace("{" + "username" + "}", "$username")
        )
        val response = request<kotlin.Array<PostDto>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<PostDto>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> {
                showMessage("Client error: ${(response as ClientError<*>).body as? String ?: "Client error"}")
                (response as Success<*>).data as Array<PostDto>
            }
            ResponseType.ServerError -> {
                showMessage("Server error: ${(response as ClientError<*>).body as? String ?: "Server error"}")
                (response as Success<*>).data as Array<PostDto>
            }
        }
    }
    /**
     * 
     * 
     * @param body  
     * @param authorization  
     * @return PostDto
     */
    @Suppress("UNCHECKED_CAST")
    fun save2(body: PostDto, authorization: kotlin.String): PostDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        authorization.apply {
            localVariableHeaders["Authorization"] = this.toString()
        }
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "*/*"
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/posts/", headers = localVariableHeaders
        )
        val response = request<PostDto>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PostDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> {
                showMessage("Client error: ${(response as ClientError<*>).body as? String ?: "Client error"}")
                (response as Success<*>).data as PostDto
            }
            ResponseType.ServerError -> {
                showMessage("Server error: ${(response as ClientError<*>).body as? String ?: "Server error"}")
                (response as Success<*>).data as PostDto
            }
        }
    }
    /**
     * 
     * 
     * @param content  
     * @return kotlin.Array<PostDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun searchByTitle(content: kotlin.String): kotlin.Array<PostDto> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/posts/search/{content}".replace("{" + "content" + "}", "$content")
        )
        val response = request<kotlin.Array<PostDto>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<PostDto>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> {
                showMessage("Client error: ${(response as ClientError<*>).body as? String ?: "Client error"}")
                (response as Success<*>).data as Array<PostDto>
            }
            ResponseType.ServerError -> {
                showMessage("Server error: ${(response as ClientError<*>).body as? String ?: "Server error"}")
                (response as Success<*>).data as Array<PostDto>
            }
        }
    }
}

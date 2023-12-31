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

import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.models.ServiceError

import it.unical.demacs.enterprise.fintedapp.infrastructure.*

class PostControllerApi(basePath: kotlin.String = "http://localhost:8080") : ApiClient(basePath) {

    /**
     * 
     * 
     * @param id  
     * @return void
     */
    fun delete2(id: kotlin.Long): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/posts/{id}".replace("{" + "id" + "}", "$id")
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
     * @param id  
     * @return PostDto
     */
    @Suppress("UNCHECKED_CAST")
    fun get1(id: kotlin.Long): PostDto {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/posts/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<PostDto>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PostDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
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
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @return PostDto
     */
    @Suppress("UNCHECKED_CAST")
    fun save2(body: PostDto): PostDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/posts/"
        )
        val response = request<PostDto>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PostDto
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
     * @return PostDto
     */
    @Suppress("UNCHECKED_CAST")
    fun update1(body: PostDto): PostDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/posts/"
        )
        val response = request<PostDto>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PostDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}

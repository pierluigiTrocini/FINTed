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
package it.unical.demacs.enterprise.fintedapp.models

import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.models.UserDto

/**
 * 
 * @param id 
 * @param post 
 * @param user 
 * @param offer 
 * @param publishDate 
 */
data class OfferDto (

    val id: kotlin.Long? = null,
    val post: PostDto? = null,
    val user: UserDto? = null,
    val offer: kotlin.Long? = null,
    val publishDate: java.time.LocalDateTime? = null
) {
}
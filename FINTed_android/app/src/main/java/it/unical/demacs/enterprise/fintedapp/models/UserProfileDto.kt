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
import it.unical.demacs.enterprise.fintedapp.models.ReviewDto

/**
 * 
 * @param id 
 * @param firstName 
 * @param lastName 
 * @param username 
 * @param registrationDate 
 * @param credentialsEmail 
 * @param addressRoute 
 * @param addressNumber 
 * @param addressCity 
 * @param publishedPosts 
 * @param receivedReviews 
 */
data class UserProfileDto (

    val id: kotlin.Long? = null,
    val firstName: kotlin.String? = null,
    val lastName: kotlin.String? = null,
    val username: kotlin.String? = null,
    val registrationDate: java.time.LocalDateTime? = null,
    val credentialsEmail: kotlin.String? = null,
    val addressRoute: kotlin.String? = null,
    val addressNumber: kotlin.String? = null,
    val addressCity: kotlin.String? = null,
    val publishedPosts: kotlin.Array<PostDto>? = null,
    val receivedReviews: kotlin.Array<ReviewDto>? = null
) {
}
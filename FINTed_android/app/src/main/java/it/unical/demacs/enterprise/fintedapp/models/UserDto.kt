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


/**
 * 
 * @param id 
 * @param firstName 
 * @param lastName 
 * @param username 
 * @param registrationDate 
 */
data class UserDto (

    val id: kotlin.Long? = null,
    val firstName: kotlin.String? = null,
    val lastName: kotlin.String? = null,
    val username: kotlin.String? = null,
    val registrationDate: java.util.Date? = null,
) {
}
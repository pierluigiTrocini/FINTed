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
 * @param timestamp 
 * @param url 
 * @param message 
 */
data class ServiceError (

    val timestamp:  java.util.Date? = null,
    val url: kotlin.String? = null,
    val message: kotlin.String? = null
) {
}
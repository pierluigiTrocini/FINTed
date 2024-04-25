package it.unical.demacs.enterprise.fintedapp.models

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
    val registrationDate: java.util.Date? = null,
    val credentialsEmail: kotlin.String? = null,
    val addressRoute: kotlin.String? = null,
    val addressNumber: kotlin.String? = null,
    val addressCity: kotlin.String? = null,
    val publishedPosts: kotlin.Array<PostDto>? = null,
    val receivedReviews: kotlin.Array<ReviewDto>? = null
) {
}

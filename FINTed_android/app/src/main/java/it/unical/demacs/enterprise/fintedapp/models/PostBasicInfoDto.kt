package it.unical.demacs.enterprise.fintedapp.models

/**
 *
 * @param id
 * @param title
 * @param sellerId
 * @param sellerUsername
 * @param publishedDate
 * @param startingPrice
 * @param status
 */

data class PostBasicInfoDto(
    val id: kotlin.Long? = null,
    val title: kotlin.String? = null,
    val sellerId: kotlin.Long? = null,
    val sellerUsername: kotlin.String? = null,
    val publishedDate: java.util.Date? = null,
    val startingPrice: kotlin.Long? = null,
    val status: PostDto.Status? = null
)

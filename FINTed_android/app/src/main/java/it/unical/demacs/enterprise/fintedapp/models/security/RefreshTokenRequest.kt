package it.unical.demacs.enterprise.fintedapp.models.security

data class RefreshTokenRequest(
    val grant_type: String,
    val client_id: String,
    val refresh_token: String
)

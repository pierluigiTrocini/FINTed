package it.unical.demacs.enterprise.fintedapp.models.security

data class AccessTokenRequest (
    val grant_type: String,
    val client_id: String,
    val username: String,
    val password: String
)
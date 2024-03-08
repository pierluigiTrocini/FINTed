package it.unical.demacs.enterprise.fintedapp.apis

data class ApiResources(
    val backendUrl: String = "http://192.168.1.117:8080",
    val authUrl: String = "http://localhost:8090",

    val authPath: String = "/auth/realms/finted/protocol/openid-connect/token",
    val grant_type_password: String = "password",
    val grant_type_refresh: String = "refresh_token",
    val client_id: String = "finted-client"
)

package it.unical.demacs.enterprise.fintedapp.apis

data class ApiResources(
    val backendUrl: String = "http://192.168.1.117:8080",

    val authUrl: String = "http://192.168.1.117:8090",

    val authPath: String = "/realms/finted/protocol/openid-connect/token",
    val logoutPath: String = "/realms/finted/protocol/openid-connect/logout",
    val tokenIntrospection: String = "/realms/finted/protocol/openid-connect/token/introspect",
    val grant_type_password: String = "password",
    val grant_type_refresh: String = "refresh_token",
    val client_id: String = "finted",
    val client_secret: String = "dWqsIb2MFlmFhf5t0AuROGYbXnuTYuJc"

)

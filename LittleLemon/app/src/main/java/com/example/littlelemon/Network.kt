package com.example.littlelemon

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
)

val httpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        val menuJson = Json { ignoreUnknownKeys = true }
        json(menuJson) // matches Content-Type: application/json
        // GitHub's raw file server sends .json files as text/plain, so Ktor
        // needs to be told to also run the JSON parser on that content type,
        // otherwise it throws NoTransformationFoundException on a 200 OK
        // response that is genuinely valid JSON.
        json(menuJson, contentType = ContentType.Text.Plain)
    }
}

suspend fun fetchMenu(): MenuNetwork {
    val url = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    return httpClient.get(url).body()
}

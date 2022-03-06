package it.paganellif.gitlabcli.api

import io.ktor.client.statement.*

interface BaseApi {
    val apiUrl: String
    val token: String
    val sslVerify: Boolean

    fun get(endpoint: String): HttpResponse
    fun post(endpoint: String): HttpResponse
    fun checkResponse(response: HttpResponse): Boolean
}
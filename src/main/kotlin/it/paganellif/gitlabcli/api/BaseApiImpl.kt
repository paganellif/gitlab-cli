package it.paganellif.gitlabcli.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

open class BaseApiImpl(override val apiUrl: String,
                       override val token: String = System.getenv("GITLAB_TOKEN"),
                       override val sslVerify: Boolean = false) : BaseApi {
    private val client = HttpClient(CIO)
    private val logger = KotlinLogging.logger {}

    override fun get(endpoint: String): HttpResponse {
        val response: HttpResponse
        val request = "$apiUrl/$endpoint"
        logger.debug { "GET -> $request" }
        runBlocking {
            response = client.get(request){
                if(token != null && token != "")
                    headers.append("PRIVATE-TOKEN", token)
                else
                    logger.debug { "Found no env var called GITLAB_TOKEN" }
            }
        }
        return response
    }

    override fun post(endpoint: String): HttpResponse {
        val response: HttpResponse
        val request = "$apiUrl/$endpoint"
        logger.debug { "POST -> $request" }
        runBlocking {
            response = client.post(request){
                if(token != null && token != "")
                    headers.append("PRIVATE-TOKEN", token)
                else
                    logger.debug { "Found no env var called GITLAB_TOKEN" }
            }
        }
        return response
    }

    override fun checkResponse(response: HttpResponse): Boolean {
        return response.status.isSuccess() &&
                (response.status == HttpStatusCode.OK || response.status == HttpStatusCode.Accepted)
    }

}
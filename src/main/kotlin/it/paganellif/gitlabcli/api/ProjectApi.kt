package it.paganellif.gitlabcli.api

import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import java.net.URLEncoder

class ProjectApi(override val apiUrl: String,
                 override val sslVerify: Boolean = false): BaseApiImpl(apiUrl, sslVerify = sslVerify) {

    /**
     * https://docs.gitlab.com/ee/api/projects.html#get-single-project
     * GET /projects/:id
     * url - The ID of a project or URL-encoded NAMESPACE/PROJECT_NAME of the project owned by the authenticated user
     */
    fun getProject(url: String, option: String = ""): String {
        val result: String
        runBlocking {
            val urlEncoded: String = URLEncoder.encode(url, "utf-8")
            result = get("projects/$urlEncoded$option").readText()
        }
        return result
    }

    /**
     * https://docs.gitlab.com/ee/api/project_level_variables.html#show-variable-details
     * GET /projects/:id/variables/:key
     * url - The ID of a project or URL-encoded NAMESPACE/PROJECT_NAME of the project owned by the authenticated user
     * key - The key of a variable
     */
    fun getProjectVar(url: String, key: String): String {
        val result: String
        runBlocking {
            val urlEncoded: String = URLEncoder.encode(url, "utf-8")
            result = get("projects/$urlEncoded/variables/$key").readText()
        }
        return result
    }

}
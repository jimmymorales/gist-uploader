package com.kotlinerskt.cli.giskt

import com.kotlinerskt.cli.giskt.github.GistContent
import com.kotlinerskt.cli.giskt.github.GistFiles
import com.kotlinerskt.cli.giskt.github.GistRequest
import com.kotlinerskt.cli.giskt.network.GithubClient
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import java.io.File

@KtorExperimentalAPI
fun GistRequest.uploadFile() {
    runBlocking {
        val githubClient = GithubClient(SecretToken.TOKEN)
        githubClient.createGiskt(this@uploadFile)
    }
}

fun GisktFile.createGistRequest(isPublic: Boolean, description: String): GistRequest {
    val files: GistFiles = mapOf<String, GistContent>(
        name to GistContent(content)
    )

    return GistRequest(
        public = isPublic,
        files = files,
        description = description
    )
}

fun List<GisktFile>.createGistRequest(isPublic: Boolean, description: String): GistRequest {
    val files: GistFiles = map { it.name to GistContent(it.content) }.toMap()

    return GistRequest(
        public = isPublic,
        files = files,
        description = description
    )
}

fun createGisktFile(gistFileName: File): GisktFile {
    val fileName = gistFileName.name
    val content = gistFileName.readLines().joinToString("\n")
    return GisktFile(fileName, content)
}

@Serializable
data class GisktFile(val name: String, val content: String)

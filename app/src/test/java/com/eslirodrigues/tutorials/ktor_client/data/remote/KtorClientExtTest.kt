package com.eslirodrigues.tutorials.ktor_client.data.remote

import java.io.FileNotFoundException
import java.net.URL

fun String.readFile(): String {
    val content: URL? = ClassLoader.getSystemResource(this)

    return content?.readText() ?: throw FileNotFoundException("file path: $this was not found")
}
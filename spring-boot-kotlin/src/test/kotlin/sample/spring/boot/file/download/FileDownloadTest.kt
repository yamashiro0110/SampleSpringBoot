package sample.spring.boot.file.download

import okhttp3.OkHttpClient
import okhttp3.Request

fun main(args: Array<String>) {
    val client = OkHttpClient.Builder().build()
    val request = Request.Builder().url("http://localhost:8080/file/download").build()
    val response = client.newCall(request).execute()
    println(response.body().string())
    response.close()
}

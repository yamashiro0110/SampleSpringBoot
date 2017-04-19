package sample.spring.boot.http

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by yamashiro-r on 2017/04/20.
 */
fun main(args: Array<String>) {
    basicAuthenticationEndPoint()
}

fun basicAuthenticationEndPoint() {
    val client = OkHttpClient.Builder()
            .authenticator { route, response ->
                val credentials = Credentials.basic("ryota", "yamashiro")
                response.request().newBuilder().header("Authorization", credentials).build()
            }
            .build()
    val request = Request.Builder().url("http://localhost:8080/").build()
    val response = client.newCall(request).execute()
    println(response.body().string())
    response.close()
}

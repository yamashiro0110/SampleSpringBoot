package sample.spring.boot.models

/**
 * Created by yamashiro-r on 2017/02/26.
 */
class PostImage(
        name: String = "",
        url: String = "",
        type: String = "",
        size: Long = 0) {
    val name = name
    val url = url
    val type = type
    val size = size
}

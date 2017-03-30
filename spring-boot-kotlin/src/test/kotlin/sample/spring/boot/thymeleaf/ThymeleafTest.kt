package sample.spring.boot.thymeleaf

import org.thymeleaf.context.Context
import org.thymeleaf.spring4.SpringTemplateEngine
import java.util.*

/**
 * Created by yamashiro-r on 2017/03/30.
 */

fun main(args: Array<String>) {
    val templateEngine = SpringTemplateEngine()
    val context = Context(Locale.JAPANESE, mapOf(
            Pair("key1", "pen"),
            Pair("key2", "apple"),
            Pair("key3", "pineapple")
    ))

    println(templateEngine.process(template, context))
}

val template = """
<html>
<head>
<title>hello thymeleaf</title>
<body>
<h1>hello thymeleaf</h1>
<p th:text="${'$'}{key3}"></p>
<p th:text="${'$'}{key2}"></p>
<p th:text="${'$'}{key1}"></p>
</body>
</head>
</html>
"""

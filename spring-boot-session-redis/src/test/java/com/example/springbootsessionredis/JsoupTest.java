package com.example.springbootsessionredis;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by yamashiro-r on 2017/06/25.
 */
public class JsoupTest {

    private String html() {
        return "";
    }

    @Test
    public void test() throws IOException {
        FileSystemResource resource = new FileSystemResource("src/test/resources/jsoup_test.html");
        Document document = Jsoup.parse(resource.getFile(), Charset.defaultCharset().name());

        document.getElementsByTag("img").forEach(it -> {
            it.attr("src", "/cdn" + it.attr("src"));
            System.out.println(it.attr("src"));
        });

        System.out.println();
        System.out.println(document.body().children());
    }

}

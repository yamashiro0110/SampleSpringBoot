package com.example.controller;

import okhttp3.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by yamashiro-r on 2017/07/08.
 */
public class FormControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FormControllerTest.class);

    String text() {
        try {
            String param = "ファン";
            return URLEncoder.encode(param, "Shift_JIS");
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void test() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();

        RequestBody requestBody = new FormBody.Builder()
                .addEncoded("text", this.text())
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/form")
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            LOGGER.info("response.code:{}", response.code());
            LOGGER.info("response.body:{}", response.body().string());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
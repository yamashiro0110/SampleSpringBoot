package com.example.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamashiro-r on 2017/04/30.
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class SleepControllerTest {

    @Test(expected = SocketTimeoutException.class)
    public void sleep() throws Exception {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/sleep")
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println(response.body().string());
            Assert.assertTrue(response.isSuccessful());
        }
    }

}

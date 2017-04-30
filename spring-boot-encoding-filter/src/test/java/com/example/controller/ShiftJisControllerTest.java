package com.example.controller;

import okhttp3.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamashiro-r on 2017/04/30.
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class ShiftJisControllerTest {
    private final Charset sjis = Charset.forName("SJIS");
    @Autowired
    private MockMvc mvc;

    @Test
    public void post() throws Exception {
        String text = "寿司、びーる";

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), new String(text.getBytes(), sjis));

        Request request = new Request.Builder()
                .url("http://localhost:8080/")
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println(response.body().string());
            Assert.assertTrue(response.isSuccessful());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postWithMock() throws Exception {
        String text = "寿司、びーる";
        this.mvc.perform(MockMvcRequestBuilders
                .post("/")
                .content(new String(text.getBytes(), sjis))
                .accept(org.springframework.http.MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void writeShiftJis() throws Exception {
        String text = new String("こんにちは".getBytes(), "SJIS");
        Path path = Paths.get("tmp", "sjis.txt");
        Files.write(path, Arrays.asList(text), Charset.forName("SJIS"));
    }

}

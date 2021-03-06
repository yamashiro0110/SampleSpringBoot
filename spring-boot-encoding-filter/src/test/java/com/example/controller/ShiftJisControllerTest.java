package com.example.controller;

import okhttp3.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamashiro-r on 2017/04/30.
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class ShiftJisControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(ShiftJisControllerTest.class);
    private final Charset sjis = Charset.forName("Shift_JIS");
    @Autowired
    private MockMvc mvc;

    private RequestBody formBody(String text) {
        return new FormBody.Builder()
                .addEncoded("text", text)
                .build();
    }

    private RequestBody requestBody() {
        return RequestBody.create(MediaType.parse("text/plain"), "寿司、ビール");
    }

    private void postBody() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = this.requestBody();
        logger.debug(ReflectionToStringBuilder.toString(requestBody, ToStringStyle.MULTI_LINE_STYLE));

        Request request = new Request.Builder()
                .url("http://localhost:8080/post/body")
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

    private void postForm(String text) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = this.formBody(text);
        logger.debug(ReflectionToStringBuilder.toString(requestBody, ToStringStyle.MULTI_LINE_STYLE));

        Request request = new Request.Builder()
                .url("http://localhost:8080/post/form")
                .post(requestBody)
                .build();

        logger.debug(ReflectionToStringBuilder.toString(request, ToStringStyle.MULTI_LINE_STYLE));

        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println(response.body().string());
            Assert.assertTrue(response.isSuccessful());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostBody() throws Exception {
        logger.debug("start postBody");
        this.postBody();
    }

    @Test
    public void testPostForm() throws Exception {
        logger.debug("start postForm");
        String text = "寿司、ビール";
        Charset shiftJis = Charset.forName("Shift_JIS");
        this.postForm(text);
        this.postForm(new String(text.getBytes(shiftJis)));
        this.postForm(new String(text.getBytes(StandardCharsets.UTF_8), shiftJis));
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

}

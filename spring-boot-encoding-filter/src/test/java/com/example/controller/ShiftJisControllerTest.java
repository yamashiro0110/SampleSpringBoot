package com.example.controller;

import okhttp3.*;
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
import java.nio.CharBuffer;
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
                .add("text", text)
                .build();
    }

    private RequestBody requestBody() {
        return RequestBody.create(MediaType.parse("text/plain"), "寿司、ビール");
    }

    private void postBody() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/post/body")
                .post(this.requestBody())
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

        Request request = new Request.Builder()
                .url("http://localhost:8080/post/form")
                .post(this.formBody(text))
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
    public void post() throws Exception {
        logger.debug("start postBody");
        this.postBody();

        logger.debug("start postForm");
        Charset shiftJis = Charset.forName("Shift_JIS");
        String text = "寿司、ビール";
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

    @Test
    public void writeShiftJis() throws Exception {
        Charset sjis = Charset.forName("Shift_JIS");
        String text = "寿司";
        logger.debug("SJISに変換されない:{}", new String(text.getBytes()));
        logger.debug("SJISに変換されない:{}", new String(text.getBytes(sjis)));
        logger.debug("SJISに変換される:{}", new String(text.getBytes(), sjis));
        logger.debug("SJISに変換される:{}", new String(text.getBytes(StandardCharsets.UTF_8), sjis));
    }

    @Test
    public void testCanDecode() {
        Charset sjis = Charset.forName("Shift_JIS");
        logger.debug("sjis.canEncode:{}", sjis.canEncode());
        logger.debug("sjis.encode:{}", sjis.encode(CharBuffer.wrap("寿司")));
    }

}

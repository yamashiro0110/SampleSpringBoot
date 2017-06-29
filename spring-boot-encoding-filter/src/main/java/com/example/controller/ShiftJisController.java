package com.example.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by yamashiro-r on 2017/04/30.
 */
@RestController
@RequestMapping("/post")
public class ShiftJisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiftJisController.class);

    @PostMapping("body")
    ResponseAdaptor postBody(@RequestBody String text) {
        ResponseAdaptor adaptor = new ResponseAdaptor(text);
        LOGGER.debug("post/body sjis:{}, sjis2utf8:{}, utf8:{}, text:{}", adaptor.sjis(), adaptor.sjis2utf8(), adaptor.utf8(), adaptor.text());
        return adaptor;
    }

    @PostMapping("form")
    ResponseAdaptor postForm(@RequestParam("text") String text) {
        ResponseAdaptor adaptor = new ResponseAdaptor(text);
        LOGGER.debug("post/form sjis:{}, sjis2utf8:{}, utf8:{}, text:{}", adaptor.sjis(), adaptor.sjis2utf8(), adaptor.utf8(), adaptor.text());
        return adaptor;
    }

    static class ResponseAdaptor {
        private final Charset shiftJis = Charset.forName("Shift_JIS");
        private final Charset utf8 = StandardCharsets.UTF_8;
        private String text = "";

        ResponseAdaptor(String text) {
            this.text = text;
        }

        @JsonProperty("text")
        String text() {
            return new String(this.text.getBytes());
        }

        @JsonProperty("sjis")
        String sjis() {
            return new String(text.getBytes(this.shiftJis));
        }

        @JsonProperty("sjis2utf8")
        String sjis2utf8() {
            return new String(text.getBytes(this.shiftJis), this.utf8);
        }

        @JsonProperty("utf82sjis")
        String utf82sjis() {
            return new String(text.getBytes(this.utf8), this.shiftJis);
        }

        @JsonProperty("utf8")
        String utf8() {
            return new String(text.getBytes(this.utf8));
        }
    }
}

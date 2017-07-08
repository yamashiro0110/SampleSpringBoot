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
        adaptor.log();
        return adaptor;
    }

    @PostMapping("form")
    ResponseAdaptor postForm(@RequestParam("text") String text) {
        ResponseAdaptor adaptor = new ResponseAdaptor(text);
        adaptor.log();
        return adaptor;
    }

    public static class ResponseAdaptor {
        private final Charset shiftJis = Charset.forName("Shift_JIS");
        private final Charset utf8 = StandardCharsets.UTF_8;
        private String text = "";

        ResponseAdaptor(String text) {
            this.text = text;
        }

        @JsonProperty("text")
        public String text() {
            return this.text;
        }

        @JsonProperty("sjis")
        public String sjis() {
            return new String(text.getBytes(this.shiftJis));
        }

        @JsonProperty("sjis2utf8")
        public String sjis2utf8() {
            return new String(text.getBytes(this.shiftJis), this.utf8);
        }

        @JsonProperty("utf82sjis")
        public String utf82sjis() {
            return new String(text.getBytes(this.utf8), this.shiftJis);
        }

        @JsonProperty("utf8")
        public String utf8() {
            return new String(text.getBytes(this.utf8));
        }

        void log() {
            LOGGER.debug("sjis:{}", this.sjis());
            log(this.sjis());

            LOGGER.debug("sjis2utf8:{}", this.sjis2utf8());
            log(this.sjis2utf8());

            LOGGER.debug("utf8:{}", this.utf8());
            log(this.utf8());

            LOGGER.debug("text:{}", this.text());
            log(this.text());
        }

        void log(String str) {
            LOGGER.debug("start print character:{}", str);

            for (Character character : str.toCharArray()) {
                LOGGER.debug("{}, {}", character, Integer.toHexString(character));
            }
        }
    }
}

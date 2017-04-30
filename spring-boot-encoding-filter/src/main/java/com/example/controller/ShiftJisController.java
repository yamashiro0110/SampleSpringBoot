package com.example.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yamashiro-r on 2017/04/30.
 */
@RestController
@RequestMapping("/")
public class ShiftJisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiftJisController.class);

    @PostMapping
    String post(@RequestBody String text) throws IOException {
        LOGGER.debug("sjis:{}", text);

        Charset sjis = Charset.forName("SJIS");
        Charset utf8 = StandardCharsets.UTF_8;

        String encodeText = new String(text.getBytes(sjis), utf8);
        LOGGER.debug("utf8:{}", encodeText);

        Path path = Paths.get("tmp", "text.log").toAbsolutePath();

        if (Files.notExists(path.getParent().toAbsolutePath())) {
            Files.createDirectory(path.getParent().toAbsolutePath());
        }

        FileUtils.writeStringToFile(path.toFile(), text, "SJIS");
        return "write:" + FileUtils.readLines(path.toFile(), utf8.name());
    }

}

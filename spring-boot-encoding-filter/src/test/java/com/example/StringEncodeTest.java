package com.example;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * Created by yamashiro-r on 2017/07/06.
 */
public class StringEncodeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringEncodeTest.class);
    private final Charset sjis = Charset.forName("Shift_JIS");

    void console(byte[] bytes) {
        LOGGER.info("start console:{}", bytes);

        for (byte b : bytes) {
            LOGGER.info(String.format("%02X", b));
        }
    }

    void writeFile(String filename, String text, Charset charset) throws IOException {
        File file = Paths.get("tmp", filename).toFile();
        FileUtils.writeStringToFile(file, text, charset.name());
    }

    @Test
    public void testConsole() throws IOException {
        String text = "寿司、ビール";
        String sjisText = new String(text.getBytes(this.sjis));
        String sjisEncodeText = new String(text.getBytes(), this.sjis);

        LOGGER.info("text:{}", text);
        this.console(text.getBytes());
        this.writeFile("utf8.txt", text, StandardCharsets.UTF_8);

        LOGGER.info("sjisText:{}", sjisText);
        this.console(sjisText.getBytes());
        this.writeFile("sjisText.txt", sjisText, this.sjis);

        LOGGER.info("sjisEncodeText:{}", sjisEncodeText);
        this.console(sjisEncodeText.getBytes());
        this.writeFile("sjisEncodeText.txt", sjisEncodeText, this.sjis);
    }

    @Test
    public void writeShiftJis() throws Exception {
        Charset sjis = Charset.forName("Shift_JIS");
        String text = "寿司";
        LOGGER.debug("SJISに変換されない:{}", new String(text.getBytes()));
        LOGGER.debug("SJISに変換されない:{}", new String(text.getBytes(sjis)));
        LOGGER.debug("SJISに変換される:{}", new String(text.getBytes(), sjis));
        LOGGER.debug("SJISに変換される:{}", new String(text.getBytes(StandardCharsets.UTF_8), sjis));
    }

    @Test
    public void testCanDecode() {
        Charset sjis = Charset.forName("Shift_JIS");
        LOGGER.debug("sjis.canEncode:{}", sjis.canEncode());
        LOGGER.debug("sjis.encode:{}", sjis.encode(CharBuffer.wrap("寿司")));
    }

}

package com.example.springbootsessionredis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.web.util.UrlUtils;

/**
 * Created by yamashiro-r on 2017/06/17.
 */
public class UrlUtilsTest {

    @Test
    public void test() {
        Assert.assertTrue(UrlUtils.isAbsoluteUrl("http://example.com"));
        Assert.assertTrue(UrlUtils.isAbsoluteUrl("http://example.com/image"));
        Assert.assertTrue(UrlUtils.isAbsoluteUrl("http://example.com/image/main.jpg"));
        Assert.assertTrue(UrlUtils.isAbsoluteUrl("https://example.com/image/main.jpg"));

        Assert.assertFalse(UrlUtils.isAbsoluteUrl("//example.com/image/main.jpg"));
        Assert.assertFalse(UrlUtils.isAbsoluteUrl("/image/main.jpg"));
        Assert.assertFalse(UrlUtils.isAbsoluteUrl("image/main.jpg"));
        Assert.assertFalse(UrlUtils.isAbsoluteUrl("/main.jpg"));
        Assert.assertFalse(UrlUtils.isAbsoluteUrl("main.jpg"));
        Assert.assertFalse(UrlUtils.isAbsoluteUrl(""));
    }

}

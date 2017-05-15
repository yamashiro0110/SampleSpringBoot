package com.example.springbootsessionredis.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by yamashiro-r on 2017/05/15.
 */
public class SampleSessionData implements Serializable {
    private LocalDateTime now = LocalDateTime.now();
    private String data = "";

    public SampleSessionData() {
    }

    public SampleSessionData(final LocalDateTime now, final String data) {
        this.now = now;
        this.data = data;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(final LocalDateTime now) {
        this.now = now;
    }

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }
}

package com.example.springbootsessionredis.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by yamashiro-r on 2017/05/15.
 */
public class SampleSessionData implements Serializable {
    private LocalDateTime now = LocalDateTime.now();
    private String data = "";
    private String uuid = UUID.randomUUID().toString();

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

    public String getUuid() {
        return uuid;
    }

    public List<String> getTextList() {
        return Arrays.asList(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public List<String> texts() {
        return this.getTextList();
    }
}

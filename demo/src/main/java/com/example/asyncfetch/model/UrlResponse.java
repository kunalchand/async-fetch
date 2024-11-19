package com.example.asyncfetch.model;

public class UrlResponse {
    private final String url;
    private final String body;

    public UrlResponse(String url, String body) {
        this.url = url;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }
}
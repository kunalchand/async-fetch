package com.example.asyncfetch;

import com.example.asyncfetch.api.UrlFetcher;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> urls = List.of(
            "https://jsonplaceholder.typicode.com/posts/1",
            "https://api.github.com/zen",
            "https://jsonplaceholder.typicode.com/posts/2"
            // "https://jsonceholder.typicde.com/posts/3"
        );

        UrlFetcher urlFetcher = new UrlFetcher();
        urlFetcher.fetchAndProcessUrls(urls);
    }
}
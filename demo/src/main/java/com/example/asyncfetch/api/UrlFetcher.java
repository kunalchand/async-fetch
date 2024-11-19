package com.example.asyncfetch.api;

import com.example.asyncfetch.model.UrlResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class UrlFetcher {
    private static final HttpClient client = HttpClient.newHttpClient();

    public void fetchAndProcessUrls(List<String> urls) {
        List<CompletableFuture<UrlResponse>> futures = urls.stream()
            .map(url -> fetchAsync(url).thenApply(body -> new UrlResponse(url, body)))
            .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenRun(() -> processFutures(futures))
            .join();
    }

    private void processFutures(List<CompletableFuture<UrlResponse>> futures) {
        futures.forEach(future -> {
            try {
                UrlResponse response = future.get();
                System.out.println("Response of " + response.getUrl());
                System.out.println(response.getBody());
                System.out.println("--------------------");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        });
    }

    private CompletableFuture<String> fetchAsync(String url) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body);
    }
}
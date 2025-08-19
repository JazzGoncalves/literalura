package com.jazz.literalura.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class GutendexClient {
    private static final String BASE = "https://gutendex.com/books/?search=";
    private final HttpClient http = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    // Retorna o PRIMEIRO resultado (ou null)
    public JsonNode searchFirstByTitle(String title) throws Exception {
        String url = BASE + URLEncoder.encode(title, StandardCharsets.UTF_8);
        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .header("Accept", "application/json")
                .GET().build();

        HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() < 200 || resp.statusCode() >= 300) {
            throw new RuntimeException("HTTP " + resp.statusCode() + ": " + resp.body());
        }

        JsonNode root = mapper.readTree(resp.body());
        JsonNode results = root.get("results");
        if (results != null && results.isArray() && results.size() > 0) {
            return results.get(0); // simplificação do desafio
        }
        return null;
    }
}

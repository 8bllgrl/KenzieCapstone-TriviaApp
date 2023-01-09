package com.kenzieacademy.eightballgirl.program2.logic;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpConnectionHelpers {

    //Description:

    //variables
    public static final String BASE_URL_PATH = "https://jservice.kenzie.academy/api/clues";

    //constructors

    //methods
    public static String sendGET(String URLString) {
        //** Start of GET request algorithm

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(URLString);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET request failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }


    }

    public static String formatQueryOnBaseUrl(int clueNumber){
        return BASE_URL_PATH + "/" + clueNumber;
    }

    //getters & setters
}

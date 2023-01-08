package com.kenzieacademy.eightballgirl.program1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzieacademy.eightballgirl.program1.clueapi.ClueDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CustomHttpClient {

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
    public static List<ClueDTO> getCluesList(String httpResponseBody) throws JsonProcessingException {

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<ClueDTO>> typeListDTO = new TypeReference<>() {};
            return objectMapper.readValue(httpResponseBody, typeListDTO);
        } catch(Exception e){
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        return new ArrayList<>();

    }
}

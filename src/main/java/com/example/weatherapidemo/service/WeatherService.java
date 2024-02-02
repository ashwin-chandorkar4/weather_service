package com.example.weatherapidemo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URI;

@Service
public class WeatherService {

    private static final String API_KEY = "ccbf1df571msh1d7babd1a6dd767p1256d3jsn74bae50818b5";
    private static final String API_HOST = "forecast9.p.rapidapi.com";
    private static final String API_URL = "https://forecast9.p.rapidapi.com/rapidapi/forecast/";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<JsonNode> getWeatherData(String city) {
        try {
            //String url = API_URL + "/summary?apiKey=" + API_KEY;
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-RapidAPI-Key", API_KEY);
            headers.add("X-RapidAPI-Host", API_HOST);

            RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(API_URL + city +"/summary/"));

            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode =objectMapper.readTree(responseEntity.getBody());
            String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(objectMapper.readTree(formattedJson));
        } catch (URISyntaxException | HttpClientErrorException | HttpServerErrorException | IOException e) {
            {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<JsonNode> getHourlyWeather(String city) {
        try {
            /*String url = API_URL + "/hourly?apiKey=" + API_KEY;*/
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-RapidAPI-Key", API_KEY);
            headers.add("X-RapidAPI-Host", API_HOST);

            RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(API_URL + city + "/hourly/"));

            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode =objectMapper.readTree(responseEntity.getBody());
            String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(objectMapper.readTree(formattedJson));
            //return objectMapper.readTree(responseEntity.getBody());
        } catch (URISyntaxException | HttpClientErrorException | HttpServerErrorException | IOException e) {
            //if (e instanceof HttpClientErrorException && ((HttpClientErrorException) e).getStatusCode() == HttpStatus.NOT_FOUND)
            {
                e.printStackTrace();
                return null;
            }
        }
    }
    //@Value("${api.url}")
    //@Value("${api.key}")
}

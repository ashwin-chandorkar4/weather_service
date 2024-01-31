package com.example.weatherapidemo.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.net.URI;

@Service
public class WeatherService {

    private static final String API_KEY = "ccbf1df571msh1d7babd1a6dd767p1256d3jsn74bae50818b5";
    private static final String API_HOST = "forecast9.p.rapidapi.com";
    private static final String API_URL = "https://forecast9.p.rapidapi.com/rapidapi/forecast/Berlin";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherData() {
        try {
            //String url = API_URL + "/summary?apiKey=" + API_KEY;
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-RapidAPI-Key", API_KEY);
            headers.add("X-RapidAPI-Host", API_HOST);

            RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(API_URL + "/summary/"));

            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
            return responseEntity.getBody();
        } catch (URISyntaxException | HttpClientErrorException | HttpServerErrorException e) {
            if (e instanceof HttpClientErrorException && ((HttpClientErrorException) e).getStatusCode() == HttpStatus.NOT_FOUND) {
                return "Weather data not found";
            } else {
                return "Error fetching weather data: " + e.getMessage();
            }
        }
    }

    public String getHourlyWeather() {
        try {
            /*String url = API_URL + "/hourly?apiKey=" + API_KEY;*/
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-RapidAPI-Key", API_KEY);
            headers.add("X-RapidAPI-Host", API_HOST);

            RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(API_URL + "/hourly/"));

            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
            return responseEntity.getBody();
        } catch (URISyntaxException | HttpClientErrorException | HttpServerErrorException e) {
            if (e instanceof HttpClientErrorException && ((HttpClientErrorException) e).getStatusCode() == HttpStatus.NOT_FOUND) {
                return "Hourly weather data not found";
            } else {
                return "Error fetching hourly weather data: " + e.getMessage();
            }
        }
    }
    //@Value("${api.url}")
    //@Value("${api.key}")
}

package com.example.weatherapidemo.controller;

import com.example.weatherapidemo.service.WeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /*@GetMapping("/")
    public String getDefault() {
        return "Default endpoint";*/

    @GetMapping("/summary")
    public ResponseEntity<JsonNode> getWeatherSummary(@RequestParam String city) {
        return weatherService.getWeatherData(city);

    }

    @GetMapping("/hourly")
    public ResponseEntity<JsonNode> getHourlyWeather(@RequestParam String city) {
        return weatherService.getWeatherData(city);
    }
}

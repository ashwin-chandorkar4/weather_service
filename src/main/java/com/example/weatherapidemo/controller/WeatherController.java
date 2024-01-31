package com.example.weatherapidemo.controller;

import com.example.weatherapidemo.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String getDefault() {
        return "Default endpoint";
    }
    @GetMapping("/summary")
    public String getWeatherSummary() {
        String weatherData = weatherService.getWeatherData();
        return "Weather Summary: " + weatherData;
    }

    @GetMapping("/hourly")
    public String getHourlyWeather() {
        String hourlyData = weatherService.getHourlyWeather();
        return "Hourly Weather: " + hourlyData;
    }
}

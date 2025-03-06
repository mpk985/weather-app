package com.mpk.backend.weather.controller;

import com.mpk.backend.weather.model.WeatherApiRequest;
import com.mpk.backend.weather.model.WeatherApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/weather")
public class WeatherController {

    /**
     * GET Endpoint for a location's current weather
     * @param weatherApiRequest
     * @return WeatherApiResponse
     */
    @GetMapping
    public ResponseEntity<WeatherApiResponse> getCurrentWeather(
            @RequestBody WeatherApiRequest weatherApiRequest
    ) {

        return ResponseEntity.ok().body(new WeatherApiResponse());
    }

}

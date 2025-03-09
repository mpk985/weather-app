package com.mpk.backend.weather.controller;

import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import com.mpk.backend.weather.model.WeatherApiRequest;
import com.mpk.backend.weather.model.WeatherApiResponse;
import com.mpk.backend.weather.service.ExternalWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    @Autowired
    private ExternalWeatherService weatherService;

    /**
     * GET Endpoint for a location's current weather
     * @param weatherApiRequest
     * @return WeatherApiResponse
     */
    @GetMapping
    public ResponseEntity<ClientForecastWeatherResponse> getWeather(
            @RequestBody WeatherApiRequest weatherApiRequest
    ) {

        return weatherService.getWeather(weatherApiRequest);
    }

}

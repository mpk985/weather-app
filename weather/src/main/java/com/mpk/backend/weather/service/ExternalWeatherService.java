package com.mpk.backend.weather.service;

import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import com.mpk.backend.weather.model.WeatherApiRequest;
import org.springframework.http.ResponseEntity;

public interface ExternalWeatherService {

    ResponseEntity<ClientForecastWeatherResponse> getWeather(WeatherApiRequest request);

}

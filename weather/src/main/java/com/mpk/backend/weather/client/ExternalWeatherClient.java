package com.mpk.backend.weather.client;

import com.mpk.backend.weather.model.ClientCurrentWeatherResponse;
import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import com.mpk.backend.weather.model.WeatherApiRequest;
import org.springframework.http.ResponseEntity;

public interface ExternalWeatherClient {

    ResponseEntity<ClientForecastWeatherResponse> getForecastWeather(String location, int days);
}

package com.mpk.backend.weather.client;

import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface ExternalWeatherClient {

    ResponseEntity<ClientForecastWeatherResponse> getForecastWeather(String location, Date date);
}

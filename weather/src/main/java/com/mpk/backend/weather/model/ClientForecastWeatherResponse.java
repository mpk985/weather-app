package com.mpk.backend.weather.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientForecastWeatherResponse {

    private Location location;
    private CurrentWeather current;
    private ForecastWeather forecast;
}

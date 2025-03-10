package com.mpk.backend.weather.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherApiResponse {
    private String temperatureHi = "150";
    private String temperatureLow = "-20";
}

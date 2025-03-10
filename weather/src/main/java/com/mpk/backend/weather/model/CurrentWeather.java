package com.mpk.backend.weather.model;

import lombok.Data;

@Data
public class CurrentWeather {

    private Double temp_f;
    private Double temp_c;
    private Integer humidity;
}

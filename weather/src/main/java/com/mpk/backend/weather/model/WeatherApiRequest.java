package com.mpk.backend.weather.model;

import lombok.Data;

@Data
public class WeatherApiRequest {

    public String location;
    public int days;
}

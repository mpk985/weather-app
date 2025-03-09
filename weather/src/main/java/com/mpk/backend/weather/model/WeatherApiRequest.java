package com.mpk.backend.weather.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class WeatherApiRequest {

    @NonNull
    public String location;
    @NonNull
    public Integer days;
}

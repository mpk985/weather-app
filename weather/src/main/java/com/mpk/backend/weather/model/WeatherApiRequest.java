package com.mpk.backend.weather.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class WeatherApiRequest {

    @NonNull
    public String location;
    public Date date;
}

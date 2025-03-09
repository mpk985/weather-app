package com.mpk.backend.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastWeather {

    @JsonProperty(value = "forecastday")
    private List<ForecastDay> forecastDay;

}

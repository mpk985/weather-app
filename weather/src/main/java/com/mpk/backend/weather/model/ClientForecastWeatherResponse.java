package com.mpk.backend.weather.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@RedisHash(timeToLive = 1800L) /*   Expires after 30 minutes */
public class ClientForecastWeatherResponse {

    private String id;
    private Location location;
    private CurrentWeather current;
    private ForecastWeather forecast;
}

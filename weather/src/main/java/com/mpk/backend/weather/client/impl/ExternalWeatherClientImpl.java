package com.mpk.backend.weather.client.impl;

import com.mpk.backend.weather.client.ExternalWeatherClient;
import com.mpk.backend.weather.model.ClientCurrentWeatherResponse;
import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * External Client for querying WeatherApi.com's free weather API
 */
@Component
public class ExternalWeatherClientImpl implements ExternalWeatherClient {

    Logger logger = LoggerFactory.getLogger(ExternalWeatherClientImpl.class);

    private RestTemplate restTemplate;
    private String clientUrl;
    private String forecastWeatherPath;
    private String apiKey;

    @Autowired
    public ExternalWeatherClientImpl(@Autowired RestTemplate restTemplate,
                                     @Value("${external.weather.api.base.url}") String clientUrl,
                                     @Value("${external.weather.api.forecast.weather.path}") String forecastWeatherPath,
                                     @Value("${external.weather.api.key}") String apiKey
                                     ) {
        this.restTemplate = restTemplate;
        this.clientUrl = clientUrl;
        this.forecastWeatherPath = forecastWeatherPath;
        this.apiKey = apiKey;
    }


    public ResponseEntity<ClientForecastWeatherResponse> getForecastWeather(String location, Date inputDate) {
        if(StringUtils.isBlank(location)){
            throw new IllegalArgumentException("Location can not be null");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date todaysDate = new Date();
        /*  If no date supplied, query using today's date */
        Date date = inputDate != null ? inputDate : new Date();

        logger.info("Calling external weather client for date: {} in location: {}", date, location);
        String forecastUrl = UriComponentsBuilder.fromUriString(clientUrl+forecastWeatherPath)
                .queryParam("key", apiKey)
                .queryParam("q", location)
                .queryParam("days", 1)
                .queryParam("dt", date)
                .build().toUriString();

        return restTemplate.getForEntity(forecastUrl, ClientForecastWeatherResponse.class);
    }
}

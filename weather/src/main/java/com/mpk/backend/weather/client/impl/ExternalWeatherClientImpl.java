package com.mpk.backend.weather.client.impl;

import com.mpk.backend.weather.client.ExternalWeatherClient;
import com.mpk.backend.weather.model.ClientCurrentWeatherResponse;
import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Component
public class ExternalWeatherClientImpl implements ExternalWeatherClient {

    Logger logger = LoggerFactory.getLogger(ExternalWeatherClientImpl.class);

    private RestTemplate restTemplate;
    private String clientUrl;
    private String currentWeatherPath;
    private String forecastWeatherPath;
    private String apiKey;

    @Autowired
    public ExternalWeatherClientImpl(@Autowired RestTemplate restTemplate,
                                     @Value("${external.weather.api.base.url}") String clientUrl,
                                     @Value("${external.weather.api.current.weather.path}") String currentWeatherPath,
                                     @Value("${external.weather.api.forecast.weather.path}") String forecastWeatherPath,
                                     @Value("${external.weather.api.key}") String apiKey
                                     ) {
        this.restTemplate = restTemplate;
        this.clientUrl = clientUrl;
        this.currentWeatherPath = currentWeatherPath;
        this.forecastWeatherPath = forecastWeatherPath;
        this.apiKey = apiKey;
    }

    public ResponseEntity<ClientCurrentWeatherResponse> getCurrentWeather(String location) {
        if(Strings.isBlank(location)){
            logger.error("Invalid input. Location can not be blank");
            return null;
        }
        logger.info("Calling external weather client for current weather: {}", location);
        String currentWeatherUri = UriComponentsBuilder.fromUriString(clientUrl+currentWeatherPath)
                .queryParam("key", apiKey)
                .queryParam("location", location)
                .build().toUriString();
        //add query params for API key and Location
        return restTemplate.getForEntity(currentWeatherUri, ClientCurrentWeatherResponse.class);
    }

    public ResponseEntity<ClientForecastWeatherResponse> getForecastWeather(String location, int days) {
        if(Strings.isBlank(location) || days < 1){
            logger.error("Invalid input. Location can not be blank & days must be > 0");
            return null;
        }
        logger.info("Calling external weather client for {}-day(s) forecasted weather: {}", days, location);
        String forecastUrl = UriComponentsBuilder.fromUriString(clientUrl+forecastWeatherPath)
                .queryParam("key", apiKey)
                .queryParam("location", location)
                .queryParam("days", days)
                .build().toUriString();

        return restTemplate.getForEntity(forecastUrl, ClientForecastWeatherResponse.class);
    }
}

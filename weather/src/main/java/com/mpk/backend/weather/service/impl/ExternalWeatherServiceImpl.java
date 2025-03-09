package com.mpk.backend.weather.service.impl;

import com.mpk.backend.weather.client.ExternalWeatherClient;
import com.mpk.backend.weather.client.impl.ExternalWeatherClientImpl;
import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import com.mpk.backend.weather.model.WeatherApiRequest;
import com.mpk.backend.weather.service.ExternalWeatherService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExternalWeatherServiceImpl implements ExternalWeatherService {

    Logger logger = LoggerFactory.getLogger(ExternalWeatherService.class);


    private ExternalWeatherClient weatherClient;

    public ExternalWeatherServiceImpl(@Autowired ExternalWeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public ResponseEntity<ClientForecastWeatherResponse> getWeather(WeatherApiRequest request) {
        if(Strings.isBlank(request.getLocation()) ||  request.getDays() < 0){
            logger.error("Invalid input. Location can not be blank & days must be >= 0");
            return null;
        }
        return weatherClient.getForecastWeather(request.getLocation(), request.getDays());
    }
}

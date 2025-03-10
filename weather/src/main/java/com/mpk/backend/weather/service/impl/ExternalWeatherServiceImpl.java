package com.mpk.backend.weather.service.impl;

import com.mpk.backend.weather.client.ExternalWeatherClient;
import com.mpk.backend.weather.client.impl.ExternalWeatherClientImpl;
import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import com.mpk.backend.weather.model.ForecastDay;
import com.mpk.backend.weather.model.WeatherApiRequest;
import com.mpk.backend.weather.repository.ForecastRepository;
import com.mpk.backend.weather.service.ExternalWeatherService;
import com.mpk.backend.weather.util.CacheUtil;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class ExternalWeatherServiceImpl implements ExternalWeatherService {

    Logger logger = LoggerFactory.getLogger(ExternalWeatherService.class);

    private ExternalWeatherClient weatherClient;

    private CacheUtil cacheUtil;

    public ExternalWeatherServiceImpl(@Autowired ExternalWeatherClient weatherClient,
                                      @Autowired CacheUtil cacheUtil){
        this.weatherClient = weatherClient;
        this.cacheUtil = cacheUtil;
    }

    public ResponseEntity<ClientForecastWeatherResponse> getWeather(WeatherApiRequest request) {
        if(Strings.isBlank(request.getLocation())){
            logger.error("Invalid input. Location can not be blank");
            throw new IllegalArgumentException();
        }

        /*  Check if forecast exists in cache, if yes return the weather */
       Optional<ClientForecastWeatherResponse> cacheResponse = cacheUtil.pollCache(request);
        if(cacheResponse.isPresent()){
            logger.info("Cache hit for id: {}", cacheResponse.get().getId());
            return ResponseEntity.ok(cacheResponse.get());
        }
        /*  If no cache hit, query external weather client and cache the response.
            TTL is set on the Model object ClientForecastWeatherResponse
        */
        ResponseEntity<ClientForecastWeatherResponse> response = weatherClient.getForecastWeather(request.getLocation(), request.getDate());
        if(Objects.nonNull(response) && Objects.nonNull(response.getBody())){
            cacheUtil.saveToCache(response.getBody(), request.getLocation(), request.getDate());
        }
        return response;
    }
}

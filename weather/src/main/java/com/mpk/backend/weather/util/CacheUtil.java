package com.mpk.backend.weather.util;

import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import com.mpk.backend.weather.model.WeatherApiRequest;
import com.mpk.backend.weather.repository.ForecastRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CacheUtil {

    Logger logger = LoggerFactory.getLogger(CacheUtil.class);


    private ForecastRepository repository;

    public CacheUtil(@Autowired ForecastRepository repository) {
        this.repository = repository;
    }

    public Optional<ClientForecastWeatherResponse> pollCache(WeatherApiRequest request){
        String id = generateCacheKey(request.getLocation(), request.getDate());
        logger.info("Polling cache with id: {}", id);
        return repository.findById(id);
    }

    public void saveToCache(ClientForecastWeatherResponse value, String location, Date date) {
        value.setId(generateCacheKey(location, date));
        logger.info("Saving to cache with id: {}", value.getId());
        repository.save(value);
    }

    public String generateCacheKey(String location, Date date) {
        return location+date;
    }
}

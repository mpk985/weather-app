package com.mpk.backend.weather.repository;

import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends CrudRepository<ClientForecastWeatherResponse, String> {
}

package com.mpk.backend.weather.service.impl;

import com.mpk.backend.weather.client.ExternalWeatherClient;
import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import com.mpk.backend.weather.model.WeatherApiRequest;
import com.mpk.backend.weather.util.CacheUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExternalWeatherServiceImplTest {

    @Mock
    private ExternalWeatherClient weatherClient;
    @Mock
    private CacheUtil cacheUtil;
    @InjectMocks
    private ExternalWeatherServiceImpl service = new ExternalWeatherServiceImpl(weatherClient, cacheUtil);

    @Test
    public void shouldThrowExceptionWhenLocationIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> service.getWeather(new WeatherApiRequest("")));
    }

    @Test
    public void cacheShouldReturnHit() {
        ClientForecastWeatherResponse response = new ClientForecastWeatherResponse();
        WeatherApiRequest request = new WeatherApiRequest("location");
        when(cacheUtil.pollCache(request)).thenReturn(Optional.of(response));
        assertTrue(service.getWeather(request).getBody() == response);
    }

    @Test
    public void cacheShouldMissAndCallExternalApi() {
        ClientForecastWeatherResponse response = new ClientForecastWeatherResponse();
        WeatherApiRequest request = new WeatherApiRequest("location");
        when(cacheUtil.pollCache(request)).thenReturn(Optional.empty());
        when(service.getWeather(request)).thenReturn(ResponseEntity.ok(response));
        doNothing().when(cacheUtil).saveToCache(any(), anyString(), any());
        assertTrue(service.getWeather(request).getBody() == response);
    }

}
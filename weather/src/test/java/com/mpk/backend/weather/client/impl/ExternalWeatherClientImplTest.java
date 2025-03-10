package com.mpk.backend.weather.client.impl;

import com.mpk.backend.weather.client.ExternalWeatherClient;
import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExternalWeatherClientImplTest {

    @Mock
    private RestTemplate restTemplate;
    private String clientUrl;
    private String forecastWeatherPath;
    private String apiKey;
    @InjectMocks
    private ExternalWeatherClient client = new ExternalWeatherClientImpl(restTemplate, clientUrl, forecastWeatherPath, apiKey);

    @Test
    public void shouldReturnForecastData() {
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok().build());
        ResponseEntity<ClientForecastWeatherResponse> response = client.getForecastWeather("location", null);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void shouldThrow400WhenLocationBlank() {
        assertThrows(IllegalArgumentException.class, () -> client.getForecastWeather("", null));
    }

}
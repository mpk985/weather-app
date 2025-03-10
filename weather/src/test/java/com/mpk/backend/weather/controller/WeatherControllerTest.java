package com.mpk.backend.weather.controller;

import com.mpk.backend.weather.model.ClientForecastWeatherResponse;
import com.mpk.backend.weather.model.WeatherApiRequest;
import com.mpk.backend.weather.service.ExternalWeatherService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

    @Mock
    ExternalWeatherService service = Mockito.mock();
    @InjectMocks
    WeatherController controller;

    @Test
    public void shouldReturn200WithLocationAndDays() {
        WeatherApiRequest request = new WeatherApiRequest("96150");
        ClientForecastWeatherResponse response = new ClientForecastWeatherResponse();
        when(service.getWeather(request)).thenReturn(ResponseEntity.status(200).body(response));
        ResponseEntity<ClientForecastWeatherResponse> testResponse = controller.getWeather(request);
        assertTrue(testResponse.getStatusCode() == HttpStatus.OK);
    }

}
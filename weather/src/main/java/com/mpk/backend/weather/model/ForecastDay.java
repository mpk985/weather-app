package com.mpk.backend.weather.model;

import lombok.Data;

import java.util.Date;

@Data
public class ForecastDay {

    private Date date;
    private Day day;
}

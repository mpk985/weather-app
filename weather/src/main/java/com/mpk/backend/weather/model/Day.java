package com.mpk.backend.weather.model;

import lombok.Data;

@Data
public class Day {

    private Double maxtemp_c;
    private Double maxtemp_f;
    private Double mintemp_c;
    private Double mintemp_f;
}

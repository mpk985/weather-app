package com.mpk.backend.weather.model;

import lombok.Data;

@Data
public class Address {

    public String city;
    public String state;
    public String country;
    public String postalCode;
    public Double latitude;
    public Double longitude;
}

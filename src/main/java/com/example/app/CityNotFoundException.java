package com.example.app;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String cityName) {
        super("City not found: " + cityName);
    }
}

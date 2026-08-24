package com.example.app;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
    private static final List<City> INDIAN_CITIES = List.of(
            new City("Mumbai", "Mumbai"),
            new City("Delhi", "New Delhi"),
            new City("Kolkata", "Kolkata"),
            new City("Chennai", "Chennai"),
            new City("Bengaluru", "Bengaluru"),
            new City("Hyderabad", "Hyderabad"),
            new City("Jaipur", "Jaipur"),
            new City("Lucknow", "Lucknow"));

    @GetMapping
    public ResponseEntity<List<City>> listCities(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit) {
        int safeOffset = Math.max(0, offset);
        int safeLimit = Math.min(20, Math.max(0, limit));
        if (safeOffset >= INDIAN_CITIES.size() || safeLimit == 0) {
            return ResponseEntity.ok(List.of());
        }
        int end = Math.min(INDIAN_CITIES.size(), safeOffset + safeLimit);
        return ResponseEntity.ok(INDIAN_CITIES.subList(safeOffset, end));
    }

    @GetMapping("/capitals")
    public ResponseEntity<List<City>> allCitiesAndCapitals() {
        return ResponseEntity.ok(INDIAN_CITIES);
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<City> capitalForCity(@PathVariable String cityName) {
        return INDIAN_CITIES.stream()
                .filter(city -> city.name().equalsIgnoreCase(cityName.trim()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CityNotFoundException(cityName));
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCityNotFound(CityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", exception.getMessage()));
    }
}

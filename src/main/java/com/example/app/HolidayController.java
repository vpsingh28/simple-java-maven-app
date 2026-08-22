package com.example.app;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/holidays")
public class HolidayController {
    private static final List<Map<String, String>> INDIA_HOLIDAYS = List.of(
            Map.of("date", "2025-01-26", "name", "Republic Day"),
            Map.of("date", "2025-03-14", "name", "Holi"),
            Map.of("date", "2025-04-18", "name", "Good Friday"),
            Map.of("date", "2025-05-12", "name", "Buddha Purnima"),
            Map.of("date", "2025-08-15", "name", "Independence Day"),
            Map.of("date", "2025-10-02", "name", "Gandhi Jayanti"),
            Map.of("date", "2025-10-20", "name", "Diwali"),
            Map.of("date", "2025-12-25", "name", "Christmas Day"));

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> listHolidays(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit) {
        int safeOffset = Math.max(0, offset);
        int safeLimit = Math.min(20, Math.max(0, limit));
        if (safeOffset >= INDIA_HOLIDAYS.size() || safeLimit == 0) {
            return ResponseEntity.ok(List.of());
        }
        int end = Math.min(INDIA_HOLIDAYS.size(), safeOffset + safeLimit);
        return ResponseEntity.ok(INDIA_HOLIDAYS.subList(safeOffset, end));
    }
}

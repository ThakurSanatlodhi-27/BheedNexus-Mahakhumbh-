package com.bheednexusbysanat.demo.controller;

import com.bheednexusbysanat.demo.service.MeasurementService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {

        long totalUsers = measurementService.totalUsers();
        long policeVerified = measurementService.verifiedPolice();
        long policeUnverified = measurementService.unverifiedPolice();
        String alert = measurementService.generateAlert();

        return Map.of(
                "totalUsers", totalUsers,
                "policeVerified", policeVerified,
                "policeUnverified", policeUnverified,
                "alert", alert
        );
    }
}

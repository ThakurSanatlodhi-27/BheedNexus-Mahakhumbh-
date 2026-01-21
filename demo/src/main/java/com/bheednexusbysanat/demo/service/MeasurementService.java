package com.bheednexusbysanat.demo.service;



import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

    private final UserService userService;
    private final PoliceService policeService;

    public MeasurementService(UserService userService, PoliceService policeService) {
        this.userService = userService;
        this.policeService = policeService;
    }

    public long totalUsers() {
        return userService.totalUsers();
    }

    public long verifiedPolice() {
        return policeService.verifiedPolice();
    }

    public long unverifiedPolice() {
        return policeService.unverifiedPolice();
    }

    public String generateAlert() {
        long unverified = policeService.unverifiedPolice();

        if (unverified > 10) {
            return "⚠ ALERT: More than 10 police officers are unverified!";
        }
        if (unverified > 0) {
            return "⚠ Some police officers are still unverified.";
        }
        return "✔ All police officers are verified.";
    }
}

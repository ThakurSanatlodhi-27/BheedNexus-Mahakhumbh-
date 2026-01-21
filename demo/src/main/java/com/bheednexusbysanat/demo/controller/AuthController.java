package com.bheednexusbysanat.demo.controller;

import com.bheednexusbysanat.demo.entity.UserRegistration;
import com.bheednexusbysanat.demo.entity.types.TravelModeType;
import com.bheednexusbysanat.demo.repository.UserRegistrationRepository;
import com.bheednexusbysanat.demo.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final OtpService otpService;
    private final UserRegistrationRepository userRepo;

    public AuthController(OtpService otpService, UserRegistrationRepository userRepo) {
        this.otpService = otpService;
        this.userRepo = userRepo;
    }

    // ===================== REQUEST OTP =====================
    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody Map<String, String> body) {

        String mobile = body.get("mobileNumber");

        if (mobile == null || mobile.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "mobileNumber is required"));
        }

        String otp = otpService.generateAndSaveOtp(mobile);

        return ResponseEntity.ok(
                Map.of("message", "OTP generated successfully", "otp", otp)
        );
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body) {

        String mobile = body.get("mobileNumber");
        String otp = body.get("otp");

        if (mobile == null || otp == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "mobileNumber and otp required"));
        }

        boolean verified = otpService.verifyOtp(mobile, otp);

        if (!verified) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid or expired OTP"));
        }

        userRepo.findByMobileNumber(mobile).ifPresent(user -> {
            user.setVerified(true);
            userRepo.save(user);
        });

        return ResponseEntity.ok(
                Map.of("message", "OTP verified successfully")
        );
    }


    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> body) {

        String mobile = body.get("mobileNumber");
        String aadhar = body.get("aadharNumber");

        if (mobile == null || aadhar == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "mobileNumber and aadharNumber required"));
        }

        if (userRepo.existsByAadharNumber(aadhar)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Aadhar already registered"));
        }

        UserRegistration user = new UserRegistration();

        user.setUsername(body.get("username"));
        user.setMobileNumber(mobile);
        user.setAadharNumber(aadhar);

        // Age
        try {
            user.setAge(Integer.parseInt(body.getOrDefault("age", "0")));
        } catch (Exception ignored) {}

        // Travel Mode (STRING -> ENUM)
        try {
            TravelModeType mode = TravelModeType.valueOf(
                    body.get("travelMode").toUpperCase()
            );
            user.setTravelMode(mode);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid travelMode"));
        }

        // Dates (yyyy-MM-dd)
        try {
            user.setFromDate(LocalDate.parse(body.get("fromDate")));
            user.setToDate(LocalDate.parse(body.get("toDate")));
        } catch (Exception ignored) {}

        user.setStateName(body.get("stateName"));
        user.setDistrictName(body.get("districtName"));
        user.setNearestPoliceStation(body.get("nearestPoliceStation"));

        user.setVerified(false);

        userRepo.save(user);

        return ResponseEntity.ok(
                Map.of("message", "User registered successfully", "userId", user.getId())
        );
    }
}

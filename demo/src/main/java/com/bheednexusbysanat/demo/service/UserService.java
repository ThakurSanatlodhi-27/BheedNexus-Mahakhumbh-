package com.bheednexusbysanat.demo.service;

import com.bheednexusbysanat.demo.entity.UserRegistration;
import com.bheednexusbysanat.demo.repository.UserRegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRegistrationRepository userRepo;
    private final OtpService otpService;

    public UserService(UserRegistrationRepository userRepo, OtpService otpService) {
        this.userRepo = userRepo;
        this.otpService = otpService;
    }

    // Create new user
    public UserRegistration createUser(UserRegistration user) {
        return userRepo.save(user);
    }

    // Find by mobile number
    public Optional<UserRegistration> findByMobile(String mobile) {
        return userRepo.findByMobileNumber(mobile);
    }

    // Mark verified after OTP success
    public void markVerified(UserRegistration user) {
        user.setVerified(true);
        userRepo.save(user);
    }

    public long totalUsers() {
        return userRepo.count();
    }

    public long verifiedUsers() {
        return userRepo.countByVerified(true);
    }

    public long unverifiedUsers() {
        return userRepo.countByVerified(false);
    }

    // Create user + send OTP automatically
    public String createUserAndSendOtp(UserRegistration user) {
        UserRegistration saved = userRepo.save(user);
        return otpService.generateAndSaveOtp(saved.getMobileNumber());
    }
}

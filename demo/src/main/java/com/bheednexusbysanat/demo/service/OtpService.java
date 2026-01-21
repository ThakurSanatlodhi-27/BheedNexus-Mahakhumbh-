package com.bheednexusbysanat.demo.service;


import com.bheednexusbysanat.demo.entity.OtpCode;
import com.bheednexusbysanat.demo.repository.OtpCodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {
    private final OtpCodeRepository otpRepo;
    public OtpService(OtpCodeRepository otpRepo) { this.otpRepo = otpRepo; }

    public String generateAndSaveOtp(String mobile) {
        String code = String.format("%04d", new Random().nextInt(10000));
        OtpCode otp = new OtpCode();
        otp.setMobileNumber(mobile);
        otp.setCode(code);
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otp.setUsed(false);
        otpRepo.save(otp);

        // For real SMS: integrate provider here (Twilio, MSG91 etc.)
        System.out.println("OTP for " + mobile + " is: " + code + " (prints to console for testing)");
        return code;
    }

    public boolean verifyOtp(String mobile, String code) {
        var opt = otpRepo.findTopByMobileNumberAndUsedFalseOrderByIdDesc(mobile);
        if (opt.isEmpty()) return false;
        OtpCode otp = opt.get();
        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) return false;
        if (!otp.getCode().equals(code)) return false;
        otp.setUsed(true);
        otpRepo.save(otp);
        return true;
    }
}

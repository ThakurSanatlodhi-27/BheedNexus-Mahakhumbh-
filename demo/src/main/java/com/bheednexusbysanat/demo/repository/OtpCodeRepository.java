package com.bheednexusbysanat.demo.repository;



import com.bheednexusbysanat.demo.entity.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {

    Optional<OtpCode> findTopByMobileNumberAndUsedFalseOrderByIdDesc(String mobileNumber);
}

package com.bheednexusbysanat.demo.repository;



import com.bheednexusbysanat.demo.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
    Optional<UserRegistration> findByMobileNumber(String mobile);
    boolean existsByAadharNumber(String aadhar);

    long countByVerified(boolean b);
}

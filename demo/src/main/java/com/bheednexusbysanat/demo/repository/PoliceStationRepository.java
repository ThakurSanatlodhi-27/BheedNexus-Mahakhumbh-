package com.bheednexusbysanat.demo.repository;

import com.bheednexusbysanat.demo.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PoliceStationRepository extends JpaRepository<PoliceStation, Long> {

    boolean existsByInchargeId(String inchargeId);

    Optional<PoliceStation> findByInchargeId(String inchargeId);

    long countByVerified(boolean status);


    long countByVerifiedTrue();

    long countByVerifiedFalse();
}

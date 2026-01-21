package com.bheednexusbysanat.demo.repository;


import com.bheednexusbysanat.demo.entity.CheckPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckPointRepository extends JpaRepository<CheckPoint, Long> {

    boolean existsByInchargeId(String inchargeId);

    Optional<CheckPoint> findByInchargeId(String inchargeId);
}

package com.bheednexusbysanat.demo.repository;


import com.bheednexusbysanat.demo.entity.TrafficControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrafficControlRepository extends JpaRepository<TrafficControl, Long> {

    boolean existsByTrafficInchargeId(String trafficInchargeId);

    Optional<TrafficControl> findByTrafficInchargeId(String trafficInchargeId);
}

package com.bheednexusbysanat.demo.repository;


import com.bheednexusbysanat.demo.entity.VenueIncharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenueInchargeRepository extends JpaRepository<VenueIncharge, Long> {

    boolean existsByInchargeId(String inchargeId);

    Optional<VenueIncharge> findByInchargeId(String inchargeId);
}

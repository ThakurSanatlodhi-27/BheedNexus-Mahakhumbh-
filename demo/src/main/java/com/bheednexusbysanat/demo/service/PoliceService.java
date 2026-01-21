package com.bheednexusbysanat.demo.service;

import com.bheednexusbysanat.demo.entity.PoliceStation;
import com.bheednexusbysanat.demo.repository.PoliceStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceService {

    private final PoliceStationRepository policeRepo;

    public PoliceService(PoliceStationRepository policeRepo) {
        this.policeRepo = policeRepo;
    }

    // Create new police station entry
    public PoliceStation create(PoliceStation p) {
        return policeRepo.save(p);
    }

    // Find by ID
    public Optional<PoliceStation> findById(Long id) {
        return policeRepo.findById(id);
    }

    // Find all
    public List<PoliceStation> findAll() {
        return policeRepo.findAll();
    }

    // Update
    public PoliceStation update(PoliceStation p) {
        return policeRepo.save(p);
    }

    // Delete
    public void delete(Long id) {
        policeRepo.deleteById(id);
    }

    // Count verified police stations
    public long verifiedPolice() {
        return policeRepo.countByVerified(true);
    }

    // Count unverified police stations
    public long unverifiedPolice() {
        return policeRepo.countByVerified(false);
    }

    // Find by inchargeId
    public Optional<PoliceStation> findByInchargeId(String inchargeId) {
        return policeRepo.findByInchargeId(inchargeId);
    }

    // Check if inchargeId exists
    public boolean existsByInchargeId(String inchargeId) {
        return policeRepo.existsByInchargeId(inchargeId);
    }
}

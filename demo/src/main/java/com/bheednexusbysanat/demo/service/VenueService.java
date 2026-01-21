package com.bheednexusbysanat.demo.service;



import com.bheednexusbysanat.demo.entity.VenueIncharge;
import com.bheednexusbysanat.demo.repository.VenueInchargeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    private final VenueInchargeRepository venueRepo;

    public VenueService(VenueInchargeRepository venueRepo) {
        this.venueRepo = venueRepo;
    }

    public VenueIncharge create(VenueIncharge v) {
        return venueRepo.save(v);
    }

    public Optional<VenueIncharge> findById(Long id) {
        return venueRepo.findById(id);
    }

    public List<VenueIncharge> findAll() {
        return venueRepo.findAll();
    }

    public VenueIncharge update(VenueIncharge v) {
        return venueRepo.save(v);
    }

    public void delete(Long id) {
        venueRepo.deleteById(id);
    }
}

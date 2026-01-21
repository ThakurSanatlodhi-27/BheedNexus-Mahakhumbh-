package com.bheednexusbysanat.demo.service;


import com.bheednexusbysanat.demo.entity.TrafficControl;
import com.bheednexusbysanat.demo.repository.TrafficControlRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrafficService {

    private final TrafficControlRepository trafficRepo;

    public TrafficService(TrafficControlRepository trafficRepo) {
        this.trafficRepo = trafficRepo;
    }

    public TrafficControl create(TrafficControl t) {
        return trafficRepo.save(t);
    }

    public Optional<TrafficControl> findById(Long id) {
        return trafficRepo.findById(id);
    }

    public List<TrafficControl> findAll() {
        return trafficRepo.findAll();
    }

    public TrafficControl update(TrafficControl t) {
        return trafficRepo.save(t);
    }

    public void delete(Long id) {
        trafficRepo.deleteById(id);
    }
}

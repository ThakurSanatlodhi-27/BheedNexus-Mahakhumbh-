package com.bheednexusbysanat.demo.controller;

import com.bheednexusbysanat.demo.entity.TrafficControl;
import com.bheednexusbysanat.demo.repository.TrafficControlRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traffic")
public class TrafficController {

    private final TrafficControlRepository repo;

    public TrafficController(TrafficControlRepository repo) {
        this.repo = repo;
    }

    // CREATE traffic control
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TrafficControl traffic) {
        return ResponseEntity.ok(repo.save(traffic));
    }

    // LIST all traffic control data
    @GetMapping
    public ResponseEntity<List<TrafficControl>> list() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TrafficControl t) {

        TrafficControl db = repo.findById(id).orElse(null);

        if (db == null) {
            return ResponseEntity.badRequest().body("Invalid Traffic ID");
        }

        db.setStateName(t.getStateName());
        db.setDistrictName(t.getDistrictName());
        db.setTrafficInchargeName(t.getTrafficInchargeName());
        db.setTrafficInchargeMobile(t.getTrafficInchargeMobile());
        db.setTrafficInchargeId(t.getTrafficInchargeId());
        db.setCheckRoutesAvailable(t.getCheckRoutesAvailable());
        db.setTravelModeChecked(t.getTravelModeChecked());

        return ResponseEntity.ok(repo.save(db));
    }

}

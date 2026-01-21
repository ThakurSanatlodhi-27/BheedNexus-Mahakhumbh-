package com.bheednexusbysanat.demo.controller;

import com.bheednexusbysanat.demo.entity.PoliceStation;
import com.bheednexusbysanat.demo.repository.PoliceStationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/police")
public class PoliceController {

    private final PoliceStationRepository repo;

    public PoliceController(PoliceStationRepository repo) {
        this.repo = repo;
    }

    // CREATE Police Station
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PoliceStation p) {
        return ResponseEntity.ok(repo.save(p));
    }

    // LIST all
    @GetMapping
    public ResponseEntity<List<PoliceStation>> list() {
        return ResponseEntity.ok(repo.findAll());
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return repo.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body("Invalid Police ID"));
    }

    // UPDATE Police Station
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PoliceStation p) {

        PoliceStation db = repo.findById(id).orElse(null);

        if (db == null) {
            return ResponseEntity.badRequest().body("Invalid Police ID");
        }

        db.setStateName(p.getStateName());
        db.setDistrictName(p.getDistrictName());
        db.setPoliceStationName(p.getPoliceStationName());
        db.setInchargeName(p.getInchargeName());
        db.setInchargeId(p.getInchargeId());
        db.setInchargeMobile(p.getInchargeMobile());
        db.setVerified(p.getVerified());

        return ResponseEntity.ok(repo.save(db));
    }


    // DELETE Police Station
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return repo.findById(id)
                .map(db -> {
                    repo.delete(db);
                    return ResponseEntity.ok("Police record deleted");
                })
                .orElse(ResponseEntity.badRequest().body("Invalid Police ID"));
    }
}

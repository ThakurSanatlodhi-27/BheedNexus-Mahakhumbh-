package com.bheednexusbysanat.demo.controller;

import com.bheednexusbysanat.demo.entity.VenueIncharge;
import com.bheednexusbysanat.demo.repository.VenueInchargeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    private final VenueInchargeRepository repo;

    public VenueController(VenueInchargeRepository repo) {
        this.repo = repo;
    }

    // CREATE venue incharge
    @PostMapping
    public ResponseEntity<?> create(@RequestBody VenueIncharge v) {
        return ResponseEntity.ok(repo.save(v));
    }

    // LIST all venue incharges
    @GetMapping
    public ResponseEntity<List<VenueIncharge>> list() {
        return ResponseEntity.ok(repo.findAll());
    }

    // UPDATE venue incharge by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VenueIncharge v) {

        VenueIncharge db = repo.findById(id).orElse(null);

        if (db == null) {
            return ResponseEntity.badRequest().body("Invalid Venue ID");
        }

        db.setInchargeName(v.getInchargeName());
        db.setInchargeMobile(v.getInchargeMobile());
        db.setInchargeId(v.getInchargeId());
        db.setAlertInfo(v.getAlertInfo());

        return ResponseEntity.ok(repo.save(db));
    }

}

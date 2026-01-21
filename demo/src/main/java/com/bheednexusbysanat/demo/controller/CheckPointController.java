package com.bheednexusbysanat.demo.controller;

import com.bheednexusbysanat.demo.entity.CheckPoint;
import com.bheednexusbysanat.demo.repository.CheckPointRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkpoints")
public class CheckPointController {

    private final CheckPointRepository repo;

    public CheckPointController(CheckPointRepository repo) {
        this.repo = repo;
    }

    // Create checkpoint
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CheckPoint cp) {
        return ResponseEntity.ok(repo.save(cp));
    }

    // List all checkpoints
    @GetMapping
    public ResponseEntity<List<CheckPoint>> list() {
        return ResponseEntity.ok(repo.findAll());
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return repo.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body("Invalid CheckPoint ID"));
    }

    // Update (matches your CheckPoint entity fields)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CheckPoint cp) {

        CheckPoint db = repo.findById(id).orElse(null);

        if (db == null) {
            return ResponseEntity.badRequest().body("Invalid CheckPoint ID");
        }

        db.setInchargeName(cp.getInchargeName());
        db.setInchargeMobile(cp.getInchargeMobile());
        db.setInchargeId(cp.getInchargeId());
        db.setCheckUserInfo(cp.getCheckUserInfo());
        db.setCheckValidateDate(cp.getCheckValidateDate());

        return ResponseEntity.ok(repo.save(db));
    }

    // Delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return repo.findById(id).map(db -> {
            repo.deleteById(id);
            return ResponseEntity.ok().body("Deleted CheckPoint id=" + id);
        }).orElse(ResponseEntity.badRequest().body("Invalid CheckPoint ID"));
    }
}



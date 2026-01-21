package com.bheednexusbysanat.demo.service;



import com.bheednexusbysanat.demo.entity.CheckPoint;
import com.bheednexusbysanat.demo.repository.CheckPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckPointService {

    private final CheckPointRepository checkRepo;

    public CheckPointService(CheckPointRepository checkRepo) {
        this.checkRepo = checkRepo;
    }

    public CheckPoint create(CheckPoint c) {
        return checkRepo.save(c);
    }

    public Optional<CheckPoint> findById(Long id) {
        return checkRepo.findById(id);
    }

    public List<CheckPoint> findAll() {
        return checkRepo.findAll();
    }

    public CheckPoint update(CheckPoint c) {
        return checkRepo.save(c);
    }

    public void delete(Long id) {
        checkRepo.deleteById(id);
    }
}

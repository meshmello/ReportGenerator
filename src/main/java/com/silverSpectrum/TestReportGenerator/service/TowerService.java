package com.silverSpectrum.TestReportGenerator.service;

import com.silverSpectrum.TestReportGenerator.model.Tower;
import com.silverSpectrum.TestReportGenerator.repository.TowerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TowerService {
    private final TowerRepository towerRepository;

    public TowerService(TowerRepository towerRepository) {
        this.towerRepository = towerRepository;
    }

    public List<Tower> getAllTowers() {
        return (List<Tower>) towerRepository.findAll();
    }

    public Tower getTowerById(Long id) {
        return towerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tower ID: " + id));
    }

    public Tower createTower(Tower tower) {
        // Convert latitude and longitude to long values
        return towerRepository.save(tower);
    }

    public void deleteTower(Long id) {
        towerRepository.deleteById(id);
    }

    // Other methods related to towers as needed
}
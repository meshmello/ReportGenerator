package com.silverSpectrum.TestReportGenerator.service;

import com.silverSpectrum.TestReportGenerator.model.PowerLine;
import com.silverSpectrum.TestReportGenerator.model.Tower;
import com.silverSpectrum.TestReportGenerator.repository.PowerLineRepository;
import com.silverSpectrum.TestReportGenerator.repository.TowerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerLineService {
    private final PowerLineRepository powerLineRepository;
    private final TowerRepository towerRepository;

    @Autowired
    public PowerLineService(PowerLineRepository powerLineRepository, TowerRepository towerRepository) {
        this.powerLineRepository = powerLineRepository;
        this.towerRepository = towerRepository;
    }

    public List<PowerLine> getAllPowerLines() {
        return (List<PowerLine>) powerLineRepository.findAll();
    }

    public PowerLine getPowerLineById(Long id) {
        return powerLineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid power line ID: " + id));
    }

    public Tower createTower(Tower tower) {
        try {

            System.out.println("Saving tower to the database:");
            System.out.println("Latitude: " + tower.getLatitude());
            System.out.println("Longitude: " + tower.getLongitude());
            System.out.println("Tower Name: " + tower.getTowerName());
            System.out.println("Tower Number: " + tower.getTowerNumber());
            Tower savedTower = towerRepository.save(tower);
            System.out.println("Tower saved successfully. ID: " + savedTower.getId());

            return savedTower;
        } catch (Exception e) {
            System.out.println("Failed to save tower. Error: " + e.getMessage());
            e.printStackTrace();
            throw e; // or handle the exception accordingly


        }
    }

    public PowerLine createPowerLine(PowerLine powerLine) {
        return powerLineRepository.save(powerLine);
    }

    public void deletePowerLine(Long id) {
        powerLineRepository.deleteById(id);
    }

    public void createTowersBatch(List<Tower> towers) {
        try {
            towerRepository.saveAll(towers);
        } catch (Exception e) {
            System.out.println("Failed to save towers batch. Error: " + e.getMessage());
            e.printStackTrace();
            throw e; // or handle the exception accordingly
        }
    }

    public List<Tower> getTowersByPowerLineId(Long powerLineId) {
        PowerLine powerLine = powerLineRepository.findById(powerLineId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid power line ID: " + powerLineId));
        return powerLine.getTowers();
    }

    public Tower getTowerById(Long towerId) {
        return towerRepository.findById(towerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Tower Id: " + towerId));

    }
}
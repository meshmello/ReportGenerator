package com.silverSpectrum.TestReportGenerator.service;

import com.silverSpectrum.TestReportGenerator.model.Fault;
import com.silverSpectrum.TestReportGenerator.repository.FaultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaultService {
    private final FaultRepository faultRepository;

    public FaultService(FaultRepository faultRepository) {
        this.faultRepository = faultRepository;
    }

    public List<Fault> getAllFaults() {
        return (List<Fault>) faultRepository.findAll();
    }


    public Fault getFaultById(Long id) {
        return faultRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid fault ID: " + id));
    }
    public List<Fault> getFaultsByTowerId(Long towerId) {
        return faultRepository.findByTowerId(towerId);
    }

    public Fault createFault(Fault fault) {
        return faultRepository.save(fault);
    }

    public void deleteFault(Long id) {
        faultRepository.deleteById(id);
    }

    // Other methods related to faults as needed
}

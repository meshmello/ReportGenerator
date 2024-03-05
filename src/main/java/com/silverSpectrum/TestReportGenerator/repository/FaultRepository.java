package com.silverSpectrum.TestReportGenerator.repository;

import com.silverSpectrum.TestReportGenerator.model.Fault;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FaultRepository extends CrudRepository<Fault,Long> {
    List<Fault> findByTowerId(Long towerId);
}

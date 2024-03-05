package com.silverSpectrum.TestReportGenerator.repository;

import com.silverSpectrum.TestReportGenerator.model.PowerLine;
import com.silverSpectrum.TestReportGenerator.model.Tower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TowerRepository extends CrudRepository<Tower,Long> {

}

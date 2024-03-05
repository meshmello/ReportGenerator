package com.silverSpectrum.TestReportGenerator.repository;

import com.silverSpectrum.TestReportGenerator.model.PowerLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerLineRepository extends CrudRepository<PowerLine,Long> {

}

package com.silverSpectrum.TestReportGenerator.repository;

import com.silverSpectrum.TestReportGenerator.model.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report,Long> {
    List<Report> findTop10ByOrderByFinalizedDateDesc();
}

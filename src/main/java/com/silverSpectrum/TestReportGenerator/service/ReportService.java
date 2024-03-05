package com.silverSpectrum.TestReportGenerator.service;

import com.silverSpectrum.TestReportGenerator.model.Report;
import com.silverSpectrum.TestReportGenerator.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAllReports() {
        return (List<Report>) reportRepository.findAll();
    }

    public Report getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid report ID: " + id));
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public List<Report> getRecentReports() {
        return reportRepository.findTop10ByOrderByFinalizedDateDesc();
    }

    // Other methods related to reports as needed
}

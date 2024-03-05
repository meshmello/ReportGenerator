package com.silverSpectrum.TestReportGenerator.controller;

import com.silverSpectrum.TestReportGenerator.model.Report;
import com.silverSpectrum.TestReportGenerator.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportsController {
    private final ReportService reportService;

    @Autowired
    public ReportsController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String getAllReports(Model model) {
        List<Report> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);
        return "reports/list";
    }

    @GetMapping("/{id}")
    public String getReportById(@PathVariable Long id, Model model) {
        Report report = reportService.getReportById(id);
        model.addAttribute("report", report);
        return "reports/details";
    }

    @PostMapping
    public String createReport(@ModelAttribute Report report) {
        reportService.createReport(report);
        return "redirect:/reports";
    }

    @DeleteMapping("/{id}")
    public String deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return "redirect:/reports";
    }

    @GetMapping("/reports/recent")
    public String getRecentReports(Model model) {
        List<Report> recentReports = reportService.getRecentReports();
        model.addAttribute("reports", recentReports);
        return "reports";
    }

    // Other controller methods as needed
}


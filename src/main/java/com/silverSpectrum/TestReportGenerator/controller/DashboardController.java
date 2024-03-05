package com.silverSpectrum.TestReportGenerator.controller;

import com.silverSpectrum.TestReportGenerator.model.Report;
import com.silverSpectrum.TestReportGenerator.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final ReportService reportService;

    public DashboardController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        List<Report> recentReports = reportService.getRecentReports();
        model.addAttribute("reports", recentReports);
        return "dashboard";
    }
}
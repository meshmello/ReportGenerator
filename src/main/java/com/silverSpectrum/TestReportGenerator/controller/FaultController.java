package com.silverSpectrum.TestReportGenerator.controller;

import com.silverSpectrum.TestReportGenerator.model.Fault;
import com.silverSpectrum.TestReportGenerator.service.FaultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/faults")
public class FaultController {
    private final FaultService faultService;

    public FaultController(FaultService faultService) {
        this.faultService = faultService;
    }

    @GetMapping
    public String getAllFaults(Model model) {
        List<Fault> faults = faultService.getAllFaults();
        model.addAttribute("faults", faults);
        return "fault/list";
    }

    @GetMapping("/{id}")
    public String getFaultById(@PathVariable Long id, Model model) {
        Fault fault = faultService.getFaultById(id);
        model.addAttribute("fault", fault);
        return "fault/details";
    }

    @GetMapping("/create")
    public String createFaultForm(Model model) {
        // Prepare any necessary data for the form
        model.addAttribute("fault", new Fault());
        return "fault/create";
    }

    @PostMapping("/create")
    public String createFault(@ModelAttribute("fault") Fault fault) {
        faultService.createFault(fault);
        return "redirect:/faults";
    }

    @DeleteMapping("/{id}")
    public String deleteFault(@PathVariable Long id) {
        faultService.deleteFault(id);
        return "redirect:/faults";
    }

    @GetMapping("/register-line")
    public String showRegisterPowerLineForm(Model model) {
        model.addAttribute("powerLineName", "");
        return "register-power-line";
    }

}

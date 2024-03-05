package com.silverSpectrum.TestReportGenerator.controller;

import com.silverSpectrum.TestReportGenerator.model.Tower;
import com.silverSpectrum.TestReportGenerator.service.TowerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/towers")
public class TowerController {
    private final TowerService towerService;

    public TowerController(TowerService towerService) {
        this.towerService = towerService;
    }

    @GetMapping
    public String getAllTowers(Model model) {
        List<Tower> towers = towerService.getAllTowers();
        model.addAttribute("towers", towers);
        return "tower/list";
    }

    @GetMapping("/{id}")
    public String getTowerById(@PathVariable Long id, Model model) {
        Tower tower = towerService.getTowerById(id);
        model.addAttribute("tower", tower);
        return "tower/details";
    }

    @GetMapping("/create")
    public String createTowerForm(Model model) {
        // Prepare any necessary data for the form
        model.addAttribute("tower", new Tower());
        return "tower/create";
    }

    @PostMapping("/create")
    public String createTower(@ModelAttribute("tower") Tower tower) {
        towerService.createTower(tower);
        return "redirect:/towers";
    }

    @DeleteMapping("/{id}")
    public String deleteTower(@PathVariable Long id) {
        towerService.deleteTower(id);
        return "redirect:/towers";
    }

    // Other methods and mappings related to towers
}
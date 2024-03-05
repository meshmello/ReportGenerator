package com.silverSpectrum.TestReportGenerator.controller;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.silverSpectrum.TestReportGenerator.model.Fault;
import com.silverSpectrum.TestReportGenerator.model.PowerLine;
import com.silverSpectrum.TestReportGenerator.model.Tower;
import com.silverSpectrum.TestReportGenerator.service.FaultService;
import com.silverSpectrum.TestReportGenerator.service.PowerLineService;
import com.silverSpectrum.TestReportGenerator.service.TowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
@Controller
@RequestMapping("/powerlines")
public class PowerLineController {
    private final PowerLineService powerLineService;
    private final FaultService faultService;

    @Autowired
    public PowerLineController(PowerLineService powerLineService, FaultService faultService) {
        this.powerLineService = powerLineService;
        this.faultService = faultService;
    }

    @GetMapping("/register-line")
    public String showRegisterLineForm(Model model) {
        List<PowerLine> powerLines = powerLineService.getAllPowerLines();
        model.addAttribute("powerLines", powerLines);
        model.addAttribute("line", new PowerLine());
        model.addAttribute("tower", new Tower());
        return "powerline/register-line";
    }


    @GetMapping("/{id}")
    public String getPowerLineById(@PathVariable Long id, Model model) {
        PowerLine powerLine = powerLineService.getPowerLineById(id);
        model.addAttribute("powerLine", powerLine);
        return "powerline/details";
    }

    @GetMapping("/create")
    public String createPowerLineForm(Model model) {
        model.addAttribute("powerLine", new PowerLine());
        return "powerline/create";
    }

    @PostMapping("/create")
    public String createPowerLine(@ModelAttribute("powerLine") PowerLine powerLine) {
        powerLineService.createPowerLine(powerLine);
        return "redirect:/powerlines";
    }

    @DeleteMapping("/{id}")
    public String deletePowerLine(@PathVariable Long id) {
        powerLineService.deletePowerLine(id);
        return "redirect:/powerlines";
    }
    @GetMapping("/tower-list")
    public String getTowerList(@RequestParam("id") Long powerLineId, Model model) {
        List<Tower> towers = powerLineService.getTowersByPowerLineId(powerLineId);
        model.addAttribute("towers", towers);
        return "powerline/tower-list";
    }

    @GetMapping("/{powerLineId}/towers/{towerId}")
    public String getTowerDetails(@PathVariable Long powerLineId, @PathVariable Long towerId, Model model) {
        PowerLine powerLine = powerLineService.getPowerLineById(powerLineId);
        Tower tower = powerLineService.getTowerById(towerId);
        model.addAttribute("powerLine", powerLine);
        model.addAttribute("tower", tower);
        return "powerline/tower-details";
    }

    @GetMapping("/{powerLineId}/towers/{towerId}/add-fault")
    public String showAddFaultForm(@PathVariable Long powerLineId, @PathVariable Long towerId, Model model) {
        PowerLine powerLine = powerLineService.getPowerLineById(powerLineId);
        Tower tower = powerLineService.getTowerById(towerId);
        Fault newFault = new Fault();
        model.addAttribute("powerLine", powerLine);
        model.addAttribute("tower", tower);
        model.addAttribute("fault", newFault);
        return "powerline/add-fault";
    }

    @PostMapping("/{powerLineId}/towers/{towerId}/add-fault")
    public String addFault(@PathVariable Long powerLineId, @PathVariable Long towerId, @ModelAttribute Fault fault) {
        // Logic to add the fault for the specified tower
        // Redirect to the tower details page
        return "redirect:/powerlines/" + powerLineId + "/towers/" + towerId;
    }

    @GetMapping("/{powerLineId}/towers/{towerId}/faults")
    public String viewFaults(@PathVariable Long powerLineId, @PathVariable Long towerId, Model model) {
        PowerLine powerLine = powerLineService.getPowerLineById(powerLineId);
        Tower tower = powerLineService.getTowerById(towerId);
        List<Fault> faults = faultService.getFaultsByTowerId(towerId);
        model.addAttribute("powerLine", powerLine);
        model.addAttribute("tower", tower);
        model.addAttribute("faults", faults);
        return "powerline/fault-list";
    }



    @PostMapping("/register-line")
    public String registerPowerLine(@RequestParam("powerLineName") String powerLineName,
                                    @RequestParam("csvFile") MultipartFile csvFile) {
        try {
            Reader reader = new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8);
            CSVReader csvReader = new CSVReader(reader);

            // Skip the header row
            csvReader.readNext();

            CsvToBean<Tower> csvToBean = new CsvToBeanBuilder<Tower>(csvReader)
                    .withType(Tower.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',') // Use comma separator
                    .build();

            // Print raw CSV data
            System.out.println("Raw CSV Data:");
            List<String[]> csvData = csvReader.readAll();
            List<Tower> towers = csvToBean.parse();
            for (String[] row : csvData) {
                System.out.println(Arrays.toString(row));
                String s = Arrays.toString(row);
                String[] one = s.split(";");
                for (int i = 0; i < one.length; i++) {
                    System.out.println(one[i]);
                }
            }

            // Create PowerLine object
            PowerLine powerLine = new PowerLine();
            powerLine.setName(powerLineName);
            powerLineService.createPowerLine(powerLine);

            System.out.println("PowerLine Name: " + powerLine.getName());

            // Save each tower individually
            for (String[] row : csvData) {
                System.out.println(Arrays.toString(row));
                String s = Arrays.toString(row);
                String[] one = s.split(";");

                // Create a new Tower object
                Tower tower = new Tower();

                // Extract the tower number without non-numeric characters
                String towerNumberStr = one[1].replaceAll("\\D+", "");
                tower.setTowerNumber(Integer.parseInt(towerNumberStr));

                tower.setLatitude(Double.parseDouble(one[2].replaceAll("\\]", "")));
                tower.setLongitude(Double.parseDouble(one[3].replaceAll("\\]", "")));
                tower.setTowerName(one[0].substring(1));

                System.out.println("Tower Data:");
                System.out.println("Latitude: " + tower.getLatitude());
                System.out.println("Longitude: " + tower.getLongitude());
                System.out.println("Tower Name: " + tower.getTowerName());
                System.out.println("Tower Number: " + tower.getTowerNumber());

                tower.setPowerLineId(powerLine); // Set the powerLine property of the tower

                towers.add(tower);
            }

            // Call createTowersBatch to save the towers in batch
            powerLineService.createTowersBatch(towers);

            return "redirect:/powerlines/register-line?success";
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return "redirect:/powerlines/register-line?error";
        }
    }
}
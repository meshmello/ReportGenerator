package com.silverSpectrum.TestReportGenerator.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class PowerLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PowerLine() {
    }

    public PowerLine(Long id, String name, List<Report> reports, List<Tower> towers) {
        this.id = id;
        this.name = name;
        this.reports = reports;
        this.towers = towers;
    }

    private String name;

    @OneToMany(mappedBy = "powerLine", cascade = CascadeType.ALL)
    private List<Report> reports;

    @OneToMany(mappedBy = "powerLineId", cascade = CascadeType.ALL)
    private List<Tower> towers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }


}
package com.silverSpectrum.TestReportGenerator.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import java.util.List;
public class Tower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tower_number")
    @CsvBindByName(column = "tower_number")
    private int towerNumber;
    @Column(name = "tower_name")
    @CsvBindByName(column = "tower_name")
    private String towerName;
    @Column(name = "latitude")
    @CsvBindByName(column = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    @CsvBindByName(column = "longitude")
    private Double longitude;
    @ManyToOne
    @JoinColumn(name = "power_line_id")
    private PowerLine powerLineId;
    public Long getPowerLineId() {
        return powerLineId.getId();
    }
    @OneToMany(mappedBy = "tower", cascade = CascadeType.ALL)
    private List<Fault> faults;
    public Tower() {
    }

    public Tower(Long id, String tower_name, int tower_number, Double latitude, Double longitude, PowerLine power_line_id, List<Fault> faults) {
        this.id = id;
        this.towerName = tower_name;
        this.towerNumber = tower_number;
        this.latitude = latitude;
        this.longitude = longitude;
        this.powerLineId = power_line_id;
        this.faults = faults;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public int getTowerNumber() {
        return towerNumber;
    }
    public void setTowerNumber(int towerNumber) {
        this.towerNumber = towerNumber;
    }

    public String getTowerName() {
        return towerName;
    }
    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public void setPowerLineId(PowerLine power_line_id) {
        this.powerLineId = power_line_id;
    }
    public List<Fault> getFaults() {
        return faults;
    }
    public void setFaults(List<Fault> faults) {
        this.faults = faults;
    }
}
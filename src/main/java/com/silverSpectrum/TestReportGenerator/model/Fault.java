package com.silverSpectrum.TestReportGenerator.model;

import com.silverSpectrum.TestReportGenerator.model.FaultType;
import com.silverSpectrum.TestReportGenerator.model.Tower;
import jakarta.persistence.*;

import java.awt.*;

@Entity
public class Fault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Fault(Long id, String componentLocation, FaultType type, String direction, int priority, Tower tower) {
        this.id = id;
        this.componentLocation = componentLocation;
        this.type = type;
        this.direction = direction;
        this.priority = priority;
        this.tower = tower;
    }

    private String componentLocation;
    private FaultType type;
    private String direction;
    private int priority;

    @ManyToOne
    @JoinColumn(name = "tower_id")
    private Tower tower;

    public Fault() {

    }

    // Constructors, getters, and setters

    // Other methods and relationships
}
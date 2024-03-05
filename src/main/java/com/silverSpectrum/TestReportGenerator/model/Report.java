package com.silverSpectrum.TestReportGenerator.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "finalized_date")
    private LocalDate finalizedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "power_line_id")
    private PowerLine powerLine;

    public Report() {
    }

    public Report(String referenceNumber, LocalDate finalizedDate, PowerLine powerLine) {
        this.referenceNumber = referenceNumber;
        this.finalizedDate = finalizedDate;
        this.powerLine = powerLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public LocalDate getFinalizedDate() {
        return finalizedDate;
    }

    public void setFinalizedDate(LocalDate finalizedDate) {
        this.finalizedDate = finalizedDate;
    }

    public PowerLine getPowerLine() {
        return powerLine;
    }

    public void setPowerLine(PowerLine powerLine) {
        this.powerLine = powerLine;
    }

    // Other methods, relationships, and overrides
}

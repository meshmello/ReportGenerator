package com.silverSpectrum.TestReportGenerator.model;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", metadata='" + metadata + '\'' +
                ", fault=" + fault +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Fault getFault() {
        return fault;
    }

    public void setFault(Fault fault) {
        this.fault = fault;
    }

    private String metadata;

    @ManyToOne
    @JoinColumn(name = "fault_id")
    private Fault fault;

    public Image() {
    }

    public Image(Long id, String metadata, Fault fault) {
        this.id = id;
        this.metadata = metadata;
        this.fault = fault;
    }

}

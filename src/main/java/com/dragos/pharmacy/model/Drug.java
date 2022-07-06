package com.dragos.pharmacy.model;

import javax.persistence.*;

@Entity(name = "drug")
public class Drug {
    //FACI MATCHUP PE COLOANE !!! : id(nu pe ID), supply, unitsSell, name("DrugName")etc, producer, price
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(name="Supply")
    private Integer supply;
    @Column(name="UnitsSell")
    private Integer unitsSell;
    @Column(name="DrugName", nullable = false)
    private String name;
    @Column(name="DrugProducerName", nullable = false)
    private String producer;
    @Column(name="Price", nullable = false)
    private Double price;
    @Enumerated(EnumType.STRING)
    private Prescription prescription;

    public Drug() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSupply() {
        return supply;
    }

    public void setSupply(Integer supply) {
        this.supply = supply;
    }

    public Integer getUnitsSell() {
        return unitsSell;
    }

    public void setUnitsSell(Integer unitsSell) {
        this.unitsSell = unitsSell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}

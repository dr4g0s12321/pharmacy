package com.dragos.pharmacy.dto;

public class DrugDTO {
    private Long id;
    private double value;

    public DrugDTO(Long id, double value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

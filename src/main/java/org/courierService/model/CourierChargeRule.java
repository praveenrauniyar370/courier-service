package org.courierService.model;

public class CourierChargeRule {
    private final Double baseCost;
    private final Double costPerKilogram;
    private final Double costPerKilometer;

    public CourierChargeRule(Double baseCost, Double costPerKilogram, Double costPerKilometer) {
        this.baseCost = baseCost;
        this.costPerKilogram = costPerKilogram;
        this.costPerKilometer = costPerKilometer;
    }

    public Double getBaseCost() {
        return baseCost;
    }

    public Double getCostPerKilogram() {
        return costPerKilogram;
    }

    public Double getCostPerKilometer() {
        return costPerKilometer;
    }
}

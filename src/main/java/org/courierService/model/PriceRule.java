package org.courierService.model;

public class PriceRule {
    private Double baseCost;
    private Double costPerKilogram;
    private Double costPerKilometer;

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

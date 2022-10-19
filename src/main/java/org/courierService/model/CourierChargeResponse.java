package org.courierService.model;

public class CourierChargeResponse {
    private String packageName;
    private Double discountApplied;
    private Double totalCost;

    public CourierChargeResponse(String packageName, Double discountApplied, Double totalCost) {
        this.packageName = packageName;
        this.discountApplied = discountApplied;
        this.totalCost = totalCost;
    }

    public CourierChargeResponse() {
    }

    public String getPackageName() {
        return packageName;
    }

    public Double getDiscountApplied() {
        return discountApplied;
    }

    public Double getTotalCost() {
        return totalCost;
    }
}

package org.courierService.model;

public class PackageCourierChargeResponse {
    private String packageName;
    private Double discountApplied;
    private Double totalCost;

    public PackageCourierChargeResponse(String packageName, Double discountApplied, Double totalCost) {
        this.packageName = packageName;
        this.discountApplied = discountApplied;
        this.totalCost = totalCost;
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

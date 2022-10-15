package org.courierService.model;

public class PackageCourierChargeResponse {
    private String packageName;
    private Double discountApplied;
    private Double totalCost;

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

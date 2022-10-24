package org.courierService.model;

import java.util.Objects;

public class CourierChargeResponse {
    private final String packageName;
    private final Double discountApplied;
    private final Double totalCost;

    public CourierChargeResponse(String packageName, Double discountApplied, Double totalCost) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourierChargeResponse that = (CourierChargeResponse) o;
        return Objects.equals(packageName, that.packageName) && Objects.equals(discountApplied, that.discountApplied) && Objects.equals(totalCost, that.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageName, discountApplied, totalCost);
    }

    @Override
    public String toString() {
        return "Package Name=" + packageName + ", Discount Applied=" + discountApplied + ", Total Cost=" + totalCost;
    }
}

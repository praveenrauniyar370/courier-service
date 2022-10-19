package org.courierService.model;

import java.util.Objects;

public class CourierChargeWithDeliveryTime extends CourierChargeResponse{
    private Double deliveryTime;

    public CourierChargeWithDeliveryTime(String packageName, Double discountApplied, Double totalCost, Double deliveryTime) {
        super(packageName, discountApplied, totalCost);
        this.deliveryTime = deliveryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CourierChargeWithDeliveryTime that = (CourierChargeWithDeliveryTime) o;
        return Objects.equals(deliveryTime, that.deliveryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deliveryTime);
    }

    @Override
    public String toString() {
        return "CourierChargeWithDeliveryTime{" +
                "deliveryTime=" + deliveryTime +
                '}';
    }
}

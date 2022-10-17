package org.courierService.model;

public class PackageDeliveryResponse extends PackageCourierChargeResponse{
    private Double deliveryTime;

    public PackageDeliveryResponse(String packageName, Double discountApplied, Double totalCost, Double deliveryTime) {
        super(packageName, discountApplied, totalCost);
        this.deliveryTime = deliveryTime;
    }

    public PackageDeliveryResponse() {}

    public Double getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Double deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}

package org.courierService.model;

public class CourierPackage implements Comparable<CourierPackage>{
    private final String packageCode;
    private final Integer weight;
    private final Integer distanceInKm;
    private String couponCode;

    private boolean isVehicleAssigned;

    public CourierPackage(String packageCode, Integer weight, Integer distanceInKm, String couponCode) {
        this.packageCode = packageCode;
        this.weight = weight;
        this.distanceInKm = distanceInKm;
        this.couponCode = couponCode;
        this.isVehicleAssigned = false;
    }

    public CourierPackage(String packageCode, Integer weight, Integer distanceInKm) {
        this.packageCode = packageCode;
        this.weight = weight;
        this.distanceInKm = distanceInKm;
        this.isVehicleAssigned = false;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public Integer getDistanceInKm() {
        return distanceInKm;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getCouponCode() {
        return couponCode;
    }


    @Override
    public int compareTo(CourierPackage courierPackage) {
        return courierPackage.weight - this.weight;
    }

    public void setVehicleAssigned(boolean vehicleAssigned) {
        isVehicleAssigned = vehicleAssigned;
    }

    public boolean isVehicleAssigned() {
        return isVehicleAssigned;
    }
}

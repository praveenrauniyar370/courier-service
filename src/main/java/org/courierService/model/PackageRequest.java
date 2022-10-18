package org.courierService.model;

public class PackageRequest implements Comparable<PackageRequest>{
    private String packageCode;
    private Integer weight;
    private Integer distanceInKm;
    private String couponCode;

    private boolean isVehicleAssigned;

    public PackageRequest(String packageCode, Integer weight, Integer distanceInKm, String couponCode) {
        this.packageCode = packageCode;
        this.weight = weight;
        this.distanceInKm = distanceInKm;
        this.couponCode = couponCode;
        this.isVehicleAssigned = false;
    }

    public PackageRequest(String packageCode, Integer weight, Integer distanceInKm) {
        this.packageCode = packageCode;
        this.weight = weight;
        this.distanceInKm = distanceInKm;
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
    public int compareTo(PackageRequest packageRequest) {
        return packageRequest.weight - this.weight;
    }

    public void setVehicleAssigned(boolean vehicleAssigned) {
        isVehicleAssigned = vehicleAssigned;
    }

    public boolean isVehicleAssigned() {
        return isVehicleAssigned;
    }
}

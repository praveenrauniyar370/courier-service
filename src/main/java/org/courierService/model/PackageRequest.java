package org.courierService.model;

public class PackageRequest implements Comparable<PackageRequest>{
    private String packageCode;
    private Integer weight;
    private Integer distanceInKm;
    private String couponCode;

    public PackageRequest(String packageCode, Integer weight, Integer distanceInKm, String couponCode) {
        this.packageCode = packageCode;
        this.weight = weight;
        this.distanceInKm = distanceInKm;
        this.couponCode = couponCode;
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

}

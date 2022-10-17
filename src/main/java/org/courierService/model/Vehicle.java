package org.courierService.model;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private Integer vehicleId;
    private double maxSpeed;
    private Integer maxCarriableWeight;
    private Integer remainingWeightCapacity;
    private List<PackageRequest> packageRequests;

    public Vehicle(Integer vehicleId, double maxSpeed, Integer maxCarriableWeight, Integer remainingWeightCapacity) {
        this.vehicleId = vehicleId;
        this.maxSpeed = maxSpeed;
        this.maxCarriableWeight = maxCarriableWeight;
        this.remainingWeightCapacity = remainingWeightCapacity;
        this.packageRequests = new ArrayList<>();
    }


    public Integer getVehicleId() {
        return vehicleId;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public Integer getMaxCarriableWeight() {
        return maxCarriableWeight;
    }

    public Integer getRemainingWeightCapacity() {
        return remainingWeightCapacity;
    }

    public List<PackageRequest> getPackageRequests() {
        return packageRequests;
    }

    public void setRemainingWeightCapacity(Integer remainingWeightCapacity) {
        this.remainingWeightCapacity = remainingWeightCapacity;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", maxSpeed=" + maxSpeed +
                ", maxCarriableWeight=" + maxCarriableWeight +
                ", remainingWeightCapacity=" + remainingWeightCapacity +
                ", packageRequests=" + packageRequests +
                '}';
    }
}

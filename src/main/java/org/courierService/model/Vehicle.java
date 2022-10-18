package org.courierService.model;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Comparable<Vehicle>{
    private Integer vehicleId;
    private double maxSpeed;
    private Integer maxCarriableWeight;
    private Integer remainingWeightCapacity;
    private List<PackageRequest> packageRequests;

    private double vehicleStartTime;
    private double vehicleEndTime;

    public Vehicle(Integer vehicleId, double maxSpeed, Integer maxCarriableWeight, Integer remainingWeightCapacity,
                   double vehicleStartTime, double vehicleEndTime) {
        this.vehicleId = vehicleId;
        this.maxSpeed = maxSpeed;
        this.maxCarriableWeight = maxCarriableWeight;
        this.remainingWeightCapacity = remainingWeightCapacity;
        this.packageRequests = new ArrayList<>();
        this.vehicleStartTime = vehicleStartTime;
        this.vehicleEndTime = vehicleEndTime;
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

    public double getVehicleStartTime() {
        return vehicleStartTime;
    }

    public double getVehicleEndTime() {
        return vehicleEndTime;
    }

    public void setVehicleStartTime(double vehicleStartTime) {
        this.vehicleStartTime = vehicleStartTime;
    }

    public void setVehicleEndTime(double vehicleEndTime) {
        this.vehicleEndTime = vehicleEndTime;
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

    @Override
    public int compareTo(Vehicle vehicle) {
        return Double.compare(this.getVehicleEndTime(), vehicle.getVehicleEndTime());
    }
}

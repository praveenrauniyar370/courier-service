package org.courierService.model;


public class Vehicle implements Comparable<Vehicle>{
    private final Integer vehicleId;
    private final double maxSpeed;
    private final Integer maxCarriableWeight;
    private Integer remainingWeightCapacity;

    private double vehicleStartTime;
    private double vehicleEndTime;

    public Vehicle(Integer vehicleId, double maxSpeed, Integer maxCarriableWeight, Integer remainingWeightCapacity,
                   double vehicleStartTime, double vehicleEndTime) {
        this.vehicleId = vehicleId;
        this.maxSpeed = maxSpeed;
        this.maxCarriableWeight = maxCarriableWeight;
        this.remainingWeightCapacity = remainingWeightCapacity;
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
    public int compareTo(Vehicle vehicle) {
        return Double.compare(this.getVehicleEndTime(), vehicle.getVehicleEndTime());
    }
}

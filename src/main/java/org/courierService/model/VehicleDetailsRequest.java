package org.courierService.model;

public class VehicleDetailsRequest {
    private final Integer numberOfVehicle;
    private final Double maxSpeedOfVehicle;
    private final Integer maxCarriableWeight;

    public VehicleDetailsRequest(Integer numberOfVehicle, Double maxSpeedOfVehicle, Integer maxCarriableWeight) {
        this.numberOfVehicle = numberOfVehicle;
        this.maxSpeedOfVehicle = maxSpeedOfVehicle;
        this.maxCarriableWeight = maxCarriableWeight;
    }

    public Integer getNumberOfVehicle() {
        return numberOfVehicle;
    }

    public Double getMaxSpeedOfVehicle() {
        return maxSpeedOfVehicle;
    }

    public Integer getMaxCarriableWeight() {
        return maxCarriableWeight;
    }
}

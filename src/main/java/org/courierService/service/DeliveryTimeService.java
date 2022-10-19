package org.courierService.service;

import org.courierService.model.VehiclePackageMapping;
import org.courierService.model.CourierPackage;
import org.courierService.model.Vehicle;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryTimeService {
    private final List<Vehicle> vehicles;
    private final VehiclePackageMappingService vehiclePackageMappingService;

    public DeliveryTimeService(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.vehiclePackageMappingService = new VehiclePackageMappingService(vehicles.get(0).getMaxCarriableWeight());
    }



    private double calculateDeliveryTimeForAPackage(Vehicle vehicle, CourierPackage courierPackage) {
        double packageDeliveryTime = Math.floor(((vehicle.getVehicleStartTime()) + (courierPackage.getDistanceInKm() / vehicle.getMaxSpeed())) * 100) / 100;
        if(vehicle.getVehicleEndTime() < packageDeliveryTime){
            vehicle.setVehicleEndTime(packageDeliveryTime);
        }
        return packageDeliveryTime;
    }

    private void addNewRoundOfEarliestReturnedVehicle() {
        Collections.sort(vehicles);
        Vehicle earliestAvailableVehicle = vehicles.get(0);
        earliestAvailableVehicle.setVehicleStartTime(earliestAvailableVehicle.getVehicleEndTime() * 2);
        earliestAvailableVehicle.setVehicleStartTime(earliestAvailableVehicle.getVehicleEndTime() * 2);
        earliestAvailableVehicle.setRemainingWeightCapacity(earliestAvailableVehicle.getMaxCarriableWeight() );
    };

    private Map<String, Double> calculateDeliveryTime(VehiclePackageMapping vehiclePackageMapping){
        Map<String, Double> packageDeliveryTimeMap = new HashMap<>();
        Vehicle eligibleVehicle  = this.vehicles.stream()
                .filter(vehicle -> vehicle.getRemainingWeightCapacity() >= vehiclePackageMapping.getVehicleTotalLoad()).findFirst().orElse(null);
        if(eligibleVehicle != null){
            vehiclePackageMapping.getPackageOnVehicle()
                    .forEach( (packageRequest) -> {
                        double packageDeliveryResponse = calculateDeliveryTimeForAPackage(eligibleVehicle, packageRequest);
                        packageDeliveryTimeMap.put(packageRequest.getPackageCode(), packageDeliveryResponse);
                    });
            eligibleVehicle.setRemainingWeightCapacity(eligibleVehicle.getRemainingWeightCapacity() - vehiclePackageMapping.getVehicleTotalLoad());
            return packageDeliveryTimeMap;
        }
        addNewRoundOfEarliestReturnedVehicle();
        return calculateDeliveryTime(vehiclePackageMapping);
    }

    public Map<String, Double>  calculateDeliveryTimeForPackages(List<CourierPackage> courierPackages){
        Map<String, Double> packageDeliveryTimeMap = new HashMap<>();
        List<VehiclePackageMapping> vehiclesVehiclePackageMappings = vehiclePackageMappingService.createVehiclesPackageMapping(courierPackages);
        vehiclesVehiclePackageMappings.forEach(vehiclesVehiclePackageMapping -> {
            Map<String, Double> packageDeliveryResponsesForAVehicle = calculateDeliveryTime(vehiclesVehiclePackageMapping);
                    packageDeliveryTimeMap.putAll(packageDeliveryResponsesForAVehicle);
                });
        return packageDeliveryTimeMap;

    }

}

package org.courierService.service;

import org.courierService.model.PackageMapping;
import org.courierService.model.PackageRequest;
import org.courierService.model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveryTimeService {
    private List<Vehicle> vehicles;

    public DeliveryTimeService(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    private Map<String, Double> calculateDeliveryTime(PackageMapping packageMapping){
        HashMap<String, Double> packageDeliveryTimeMap = new HashMap<>();
        List<Vehicle> eligibleVehicle  = this.vehicles.stream()
                .filter(vehicle -> vehicle.getRemainingWeightCapacity() >= packageMapping.getWeight()).collect(Collectors.toList());
        if(eligibleVehicle.size() > 0){
            Vehicle vehicle = eligibleVehicle.get(0);
            vehicle.setRemainingWeightCapacity(vehicle.getRemainingWeightCapacity() - packageMapping.getWeight());
            packageMapping.getPackageRequest()
                    .stream()
                    .forEach( (packageRequest) -> {
                        double packageDeliveryResponse = calculateDeliveryTimeForAPackage(vehicle, packageRequest);
                        packageDeliveryTimeMap.put(packageRequest.getPackageCode(), packageDeliveryResponse);
                    });
            return packageDeliveryTimeMap;
        }
        addReturnedVehicle();
        return calculateDeliveryTime(packageMapping);
    }

    private double calculateDeliveryTimeForAPackage(Vehicle vehicle, PackageRequest packageRequest) {
        double packageDeliveryTime = Math.floor(((vehicle.getVehicleStartTime()) + (packageRequest.getDistanceInKm() / vehicle.getMaxSpeed())) * 100) / 100;
        if(vehicle.getVehicleEndTime() < packageDeliveryTime){
            vehicle.setVehicleEndTime(packageDeliveryTime);
        }
        return packageDeliveryTime;
    }

    private void addReturnedVehicle() {
        Collections.sort(vehicles);
        Vehicle earliestAvailableVehicle = vehicles.get(0);
        earliestAvailableVehicle.setVehicleStartTime(earliestAvailableVehicle.getVehicleEndTime() * 2);
        earliestAvailableVehicle.setVehicleStartTime(earliestAvailableVehicle.getVehicleEndTime() * 2);
        earliestAvailableVehicle.setRemainingWeightCapacity(earliestAvailableVehicle.getMaxCarriableWeight() );
    };

    public HashMap<String, Double>  calculateDeliveryTimeForPackages(List<PackageRequest> packageRequests){
        HashMap<String, Double> packageDeliveryTimeMap = new HashMap<>();
        List<PackageMapping> vehiclesPackageMappings = createVehiclesPackageMapping(packageRequests);
        vehiclesPackageMappings.forEach(vehiclesPackageMapping -> {
            Map<String, Double> packageDeliveryResponsesForAVehicle = calculateDeliveryTime(vehiclesPackageMapping);
                    packageDeliveryTimeMap.putAll(packageDeliveryResponsesForAVehicle);
                });
        return packageDeliveryTimeMap;

    }

    public List<PackageMapping> createVehiclesPackageMapping(List<PackageRequest> packageRequests){
        ArrayList<PackageMapping> packageMappings = new ArrayList<>();
        Integer vehicleMaxCapacity = this.vehicles.get(0).getMaxCarriableWeight();
        Collections.sort(packageRequests);
        for (int packageIndex = 0; packageIndex < packageRequests.size(); packageIndex++) {
            PackageRequest packageRequest = packageRequests.get(packageIndex);
            if(!packageRequest.isVehicleAssigned()){
                PackageMapping packageMapping = new PackageMapping(0);
                updatePackageRequestAndPackageMapping(packageRequest, packageMapping);
                int remainingCapacity = vehicleMaxCapacity - packageRequest.getWeight();

                while (remainingCapacity > 0){
                    List<PackageRequest> eligiblePackages = filterEligiblePackageForAVehicle(packageRequests, remainingCapacity);
                    if(eligiblePackages.size() > 0){
                        Collections.sort(eligiblePackages);
                        PackageRequest eligiblePackage = eligiblePackages.get(0);
                        updatePackageRequestAndPackageMapping(eligiblePackage, packageMapping);
                        remainingCapacity = remainingCapacity - eligiblePackage.getWeight();
                    } else {
                        remainingCapacity = 0;
                    }
                }
                packageMappings.add(packageMapping);
            }
        }
         Collections.sort(packageMappings);
        return packageMappings;
    }

    private void updatePackageRequestAndPackageMapping(PackageRequest packageRequest, PackageMapping packageMapping) {
        packageMapping.setWeight(packageMapping.getWeight() +  packageRequest.getWeight());
        packageMapping.getPackageRequest().add(packageRequest);
        packageRequest.setVehicleAssigned(true);
    }

    private List<PackageRequest> filterEligiblePackageForAVehicle(List<PackageRequest> packageRequests, Integer remainingCapacity) {
        Integer finalRemainingCapacity = remainingCapacity;
        List<PackageRequest> eligiblePackages = packageRequests.stream().filter(
                packageRequest1 -> !packageRequest1.isVehicleAssigned() &&
                         finalRemainingCapacity >= packageRequest1.getWeight())
                .collect(Collectors.toList());
        return eligiblePackages;
    }
}

package org.courierService.service;

import org.courierService.model.PackageDeliveryResponse;
import org.courierService.model.PackageMapping;
import org.courierService.model.PackageRequest;
import org.courierService.model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryTimeService {
    private List<Vehicle> vehicles;

    public DeliveryTimeService(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    private Double calculateDeliveryTime(PackageRequest packageRequest){
        List<Vehicle> eligibleVehicle  = this.vehicles.stream()
                .filter(vehicle -> vehicle.getRemainingWeightCapacity() >= packageRequest.getWeight()).collect(Collectors.toList());
        if(eligibleVehicle.size() > 0){
            Vehicle vehicle = eligibleVehicle.get(0);
            vehicle.setRemainingWeightCapacity(vehicle.getRemainingWeightCapacity() - packageRequest.getWeight());
            vehicle.getPackageRequests().add(packageRequest);
            return Math.floor(packageRequest.getDistanceInKm() / vehicle.getMaxSpeed() * 100) / 100;
        }
        return null;
    };

    public List<PackageDeliveryResponse> calculateDeliveryTime(List<PackageRequest> packageRequests){
        Collections.sort(packageRequests);
        return packageRequests.stream().map(packageRequest -> {
            PackageDeliveryResponse packageDeliveryResponse = new PackageDeliveryResponse();
            packageDeliveryResponse.setDeliveryTime(calculateDeliveryTime(packageRequest));
            return packageDeliveryResponse;
        }).collect(Collectors.toList());
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
                Integer remainingCapacity = vehicleMaxCapacity - packageRequest.getWeight();

                while (remainingCapacity > 0){
                    List<PackageRequest> eligiblePackages = filterEligiblePackage(packageRequests, remainingCapacity);
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

    private List<PackageRequest> filterEligiblePackage(List<PackageRequest> packageRequests, Integer remainingCapacity) {
        Integer finalRemainingCapacity = remainingCapacity;
        List<PackageRequest> eligiblePackages = packageRequests.stream().filter(
                packageRequest1 -> !packageRequest1.isVehicleAssigned() &&
                         finalRemainingCapacity >= packageRequest1.getWeight())
                .collect(Collectors.toList());
        return eligiblePackages;
    }
}

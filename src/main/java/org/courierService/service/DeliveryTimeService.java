package org.courierService.service;

import org.courierService.model.PackageDeliveryResponse;
import org.courierService.model.PackageRequest;
import org.courierService.model.Vehicle;

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
            System.out.println(this.vehicles);
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
}

package org.courierService.service;

import org.courierService.model.CourierPackage;
import org.courierService.model.VehiclePackageMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VehiclePackageMappingService {
    private final Integer vehicleMaxWeightCapacity;

    public VehiclePackageMappingService(Integer vehicleMaxWeightCapacity) {
        this.vehicleMaxWeightCapacity = vehicleMaxWeightCapacity;
    }

    public List<VehiclePackageMapping> createVehiclesPackageMapping(List<CourierPackage> courierPackages){
        ArrayList<VehiclePackageMapping> vehiclePackageMappings = new ArrayList<>();
        Collections.sort(courierPackages);
        for (int packageIndex = 0; packageIndex < courierPackages.size(); packageIndex++) {
            CourierPackage courierPackage = courierPackages.get(packageIndex);
            if(!courierPackage.isVehicleAssigned()){
                VehiclePackageMapping vehiclePackageMapping = getVehiclePackageMapping(courierPackages, this.vehicleMaxWeightCapacity, courierPackage);
                vehiclePackageMappings.add(vehiclePackageMapping);
            }
        }
        Collections.sort(vehiclePackageMappings);
        return vehiclePackageMappings;
    }

    private VehiclePackageMapping getVehiclePackageMapping(List<CourierPackage> courierPackages, Integer vehicleMaxCapacity, CourierPackage courierPackage) {
        VehiclePackageMapping vehiclePackageMapping = new VehiclePackageMapping(0);
        updatePackageRequestAndPackageMapping(courierPackage, vehiclePackageMapping);
        int remainingCapacity = vehicleMaxCapacity - courierPackage.getWeight();
        while (remainingCapacity > 0){
            List<CourierPackage> eligiblePackages = filterEligiblePackageForAVehicle(courierPackages, remainingCapacity);
            if(eligiblePackages.size() > 0){
                Collections.sort(eligiblePackages);
                CourierPackage eligiblePackage = eligiblePackages.get(0);
                updatePackageRequestAndPackageMapping(eligiblePackage, vehiclePackageMapping);
                remainingCapacity = remainingCapacity - eligiblePackage.getWeight();
            } else {
                remainingCapacity = 0;
            }
        }
        return vehiclePackageMapping;
    }

    private void updatePackageRequestAndPackageMapping(CourierPackage courierPackage, VehiclePackageMapping vehiclePackageMapping) {
        vehiclePackageMapping.setVehicleTotalLoad(vehiclePackageMapping.getVehicleTotalLoad() +  courierPackage.getWeight());
        vehiclePackageMapping.getPackageOnVehicle().add(courierPackage);
        courierPackage.setVehicleAssigned(true);
    }

    private List<CourierPackage> filterEligiblePackageForAVehicle(List<CourierPackage> courierPackages, Integer remainingCapacity) {
        return courierPackages.stream().filter(
                        packageRequest1 -> !packageRequest1.isVehicleAssigned() &&
                                remainingCapacity >= packageRequest1.getWeight())
                .collect(Collectors.toList());
    }
}

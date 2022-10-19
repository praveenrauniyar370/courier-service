package org.courierService.model;

import java.util.ArrayList;
import java.util.List;

public class VehiclePackageMapping implements Comparable <VehiclePackageMapping>{
    private Integer vehicleTotalLoad;
    private List<CourierPackage> packageOnVehicle;

    public VehiclePackageMapping(Integer vehicleTotalLoad) {
        this.vehicleTotalLoad = vehicleTotalLoad;
        this.packageOnVehicle = new ArrayList<>();
    }

    public Integer getVehicleTotalLoad() {
        return vehicleTotalLoad;
    }

    public void setVehicleTotalLoad(Integer vehicleTotalLoad) {
        this.vehicleTotalLoad = vehicleTotalLoad;
    }

    public List<CourierPackage> getPackageOnVehicle() {
        return packageOnVehicle;
    }

    @Override
    public int compareTo(VehiclePackageMapping vehiclePackageMapping) {
        return vehiclePackageMapping.vehicleTotalLoad - this.vehicleTotalLoad;
    }
}

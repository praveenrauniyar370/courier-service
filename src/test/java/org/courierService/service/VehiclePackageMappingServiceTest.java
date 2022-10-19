package org.courierService.service;

import org.courierService.model.CourierPackage;
import org.courierService.model.VehiclePackageMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehiclePackageMappingServiceTest {

    private VehiclePackageMappingService vehiclePackageMappingService;
    @BeforeEach
    void setUp() {
        this.vehiclePackageMappingService = new VehiclePackageMappingService(200);
    }

    @Test
    void shouldCreateVehiclesPackageMappingsForMaximizeLoadOnEachVehicle() {
        CourierPackage packageRequest1 = new CourierPackage("PKG1", 50, 30, "OFR001");
        CourierPackage packageRequest2 = new CourierPackage("PKG2", 75, 125, "OFR001");
        CourierPackage packageRequest3 = new CourierPackage("PKG3", 175, 100, "OFR001");
        CourierPackage packageRequest4 = new CourierPackage("PKG4", 110, 60, "OFR001");
        CourierPackage packageRequest5 = new CourierPackage("PKG5", 155, 95, "OFR001");

        List<VehiclePackageMapping> packageMappingsForVehicles = vehiclePackageMappingService.createVehiclesPackageMapping(Arrays.asList(packageRequest1,
                packageRequest2, packageRequest3, packageRequest4, packageRequest5));

        assertEquals(4, packageMappingsForVehicles.size());
        assertEquals(185, packageMappingsForVehicles.get(0).getVehicleTotalLoad());
        assertEquals(175, packageMappingsForVehicles.get(1).getVehicleTotalLoad());
        assertEquals(155, packageMappingsForVehicles.get(2).getVehicleTotalLoad());
        assertEquals(50, packageMappingsForVehicles.get(3).getVehicleTotalLoad());
    }
}
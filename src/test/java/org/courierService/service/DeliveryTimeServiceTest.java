package org.courierService.service;

import org.courierService.model.PackageDeliveryResponse;
import org.courierService.model.PackageMapping;
import org.courierService.model.PackageRequest;
import org.courierService.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTimeServiceTest {

    private DeliveryTimeService deliveryTimeService;
    @BeforeEach
    void setUp() {
        Vehicle vehicle1 = new Vehicle(1, 70, 200, 200);
        Vehicle vehicle2 = new Vehicle(1, 70, 200, 200);
        deliveryTimeService =  new DeliveryTimeService(Arrays.asList(vehicle1, vehicle2));
    }

    @Test
    void shouldAbleToCalculateDeliveryTimeForSinglePackage() {
        PackageRequest packageRequest = new PackageRequest("PKG1", 75, 125, "OFR001");
        List<PackageDeliveryResponse> packageDeliveryResponses = deliveryTimeService.calculateDeliveryTime(Collections.singletonList(packageRequest));
        assertEquals(1.78, packageDeliveryResponses.get(0).getDeliveryTime());
    }

    @Test
    void shouldCreateVehiclesPackageMappingsForMaximizeLoadOnEachVehicle() {
        PackageRequest packageRequest1 = new PackageRequest("PKG1", 50, 30, "OFR001");
        PackageRequest packageRequest2 = new PackageRequest("PKG2", 75, 125, "OFR001");
        PackageRequest packageRequest3 = new PackageRequest("PKG3", 175, 100, "OFR001");
        PackageRequest packageRequest4 = new PackageRequest("PKG4", 110, 60, "OFR001");
        PackageRequest packageRequest5 = new PackageRequest("PKG5", 155, 95, "OFR001");
        List<PackageMapping> packageMappingsForVehicles = deliveryTimeService.createVehiclesPackageMapping(Arrays.asList(packageRequest1,
                packageRequest2, packageRequest3, packageRequest4, packageRequest5));
        assertEquals(4, packageMappingsForVehicles.size());
        assertEquals(185, packageMappingsForVehicles.get(0).getWeight());
        assertEquals(175, packageMappingsForVehicles.get(1).getWeight());
        assertEquals(155, packageMappingsForVehicles.get(2).getWeight());
        assertEquals(50, packageMappingsForVehicles.get(3).getWeight());
    }


//    @Test
//    void shouldAbleToCalculateDeliveryTimeForMultiplePackage() {
//        PackageRequest packageRequest = new PackageRequest("PKG1", 5, 100, "OFR001");
//        List<PackageDeliveryResponse> packageDeliveryResponses = deliveryTimeService.calculateDeliveryTime(Collections.singletonList(packageRequest));
//        assertEquals(1.42d, packageDeliveryResponses.get(0).getDeliveryTime());
//    }
}
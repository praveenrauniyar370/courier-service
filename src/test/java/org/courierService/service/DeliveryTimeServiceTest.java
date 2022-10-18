package org.courierService.service;

import org.courierService.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTimeServiceTest {

    private DeliveryTimeService deliveryTimeService;
    @BeforeEach
    void setUp() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle(1, 70, 200, 200, 0d, 0d);
        Vehicle vehicle2 = new Vehicle(1, 70, 200, 200, 0d, 0d);
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        deliveryTimeService =  new DeliveryTimeService(vehicles);
    }

    @Test
    void shouldAbleToCalculateDeliveryTimeForSinglePackage() {
        PackageRequest packageRequest = new PackageRequest("PKG1", 75, 125, "OFR001");
        HashMap<String, Double> expectedPackageDeliveryTimeMap = new HashMap<>();
        expectedPackageDeliveryTimeMap.put("PKG1", 1.78d);

        HashMap<String, Double>  packageDeliveryResponses = deliveryTimeService.calculateDeliveryTimeForPackages(Collections.singletonList(packageRequest));

        assertEquals(expectedPackageDeliveryTimeMap, packageDeliveryResponses);
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

    @Test
    void shouldCalculateDeliveryTimeForEachPackage() {
        PackageRequest packageRequest1 = new PackageRequest("PKG1", 50, 30, "OFR001");
        PackageRequest packageRequest2 = new PackageRequest("PKG2", 75, 125, "OFR001");
        PackageRequest packageRequest3 = new PackageRequest("PKG3", 175, 100, "OFR001");
        PackageRequest packageRequest4 = new PackageRequest("PKG4", 110, 60, "OFR001");
        PackageRequest packageRequest5 = new PackageRequest("PKG5", 155, 95, "OFR001");
        HashMap<String, Double> expectedPackageDeliveryTimeMap = new HashMap<>();
        expectedPackageDeliveryTimeMap.put("PKG1", 3.98d);
        expectedPackageDeliveryTimeMap.put("PKG2", 1.78d);
        expectedPackageDeliveryTimeMap.put("PKG3", 1.42d);
        expectedPackageDeliveryTimeMap.put("PKG4", 0.85d);
        expectedPackageDeliveryTimeMap.put("PKG5", 4.19d);


        HashMap<String, Double> packageMappingsForVehicles = deliveryTimeService.calculateDeliveryTimeForPackages(Arrays.asList(packageRequest1,
                packageRequest2, packageRequest3, packageRequest4, packageRequest5));
        System.out.println(packageMappingsForVehicles);
        assertEquals(expectedPackageDeliveryTimeMap, packageMappingsForVehicles);

    }
}
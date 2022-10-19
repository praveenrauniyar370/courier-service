package org.courierService.service;

import org.courierService.model.CourierPackage;
import org.courierService.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        CourierPackage courierPackage = new CourierPackage("PKG1", 75, 125, "OFR001");
        HashMap<String, Double> expectedPackageDeliveryTimeMap = new HashMap<>();
        expectedPackageDeliveryTimeMap.put("PKG1", 1.78d);

        Map<String, Double> packageDeliveryResponses = deliveryTimeService.calculateDeliveryTimeForPackages(Collections.singletonList(courierPackage));

        assertEquals(expectedPackageDeliveryTimeMap, packageDeliveryResponses);
    }

    @Test
    void shouldCalculateDeliveryTimeForEachPackage() {
        CourierPackage courierPackage1 = new CourierPackage("PKG1", 50, 30, "OFR001");
        CourierPackage courierPackage2 = new CourierPackage("PKG2", 75, 125, "OFR001");
        CourierPackage courierPackage3 = new CourierPackage("PKG3", 175, 100, "OFR001");
        CourierPackage courierPackage4 = new CourierPackage("PKG4", 110, 60, "OFR001");
        CourierPackage courierPackage5 = new CourierPackage("PKG5", 155, 95, "OFR001");
        HashMap<String, Double> expectedPackageDeliveryTimeMap = new HashMap<>();
        expectedPackageDeliveryTimeMap.put("PKG1", 3.98d);
        expectedPackageDeliveryTimeMap.put("PKG2", 1.78d);
        expectedPackageDeliveryTimeMap.put("PKG3", 1.42d);
        expectedPackageDeliveryTimeMap.put("PKG4", 0.85d);
        expectedPackageDeliveryTimeMap.put("PKG5", 4.19d);


        Map<String, Double> packageMappingsForVehicles = deliveryTimeService.calculateDeliveryTimeForPackages(Arrays.asList(courierPackage1,
                courierPackage2, courierPackage3, courierPackage4, courierPackage5));

        assertEquals(expectedPackageDeliveryTimeMap, packageMappingsForVehicles);

    }
}
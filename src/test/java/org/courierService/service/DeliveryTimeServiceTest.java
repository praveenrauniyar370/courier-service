package org.courierService.service;

import org.courierService.model.PackageDeliveryResponse;
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
    void shouldAbleToCalculateDeliveryTimeSingle() {
        PackageRequest packageRequest = new PackageRequest("PKG1", 75, 125, "OFR001");
        List<PackageDeliveryResponse> packageDeliveryResponses = deliveryTimeService.calculateDeliveryTime(Collections.singletonList(packageRequest));
        assertEquals(1.78, packageDeliveryResponses.get(0).getDeliveryTime());
    }

//    @Test
//    void shouldAbleToCalculateDeliveryTimeForMultiplePackage() {
//        PackageRequest packageRequest = new PackageRequest("PKG1", 5, 100, "OFR001");
//        List<PackageDeliveryResponse> packageDeliveryResponses = deliveryTimeService.calculateDeliveryTime(Collections.singletonList(packageRequest));
//        assertEquals(1.42d, packageDeliveryResponses.get(0).getDeliveryTime());
//    }
}
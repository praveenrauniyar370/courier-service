package org.courierService.service;

import org.courierService.model.CourierChargeResponse;
import org.courierService.model.CourierChargeRule;
import org.courierService.model.CourierChargeWithDeliveryTime;
import org.courierService.model.CourierPackage;
import org.courierService.model.Offer;
import org.courierService.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourierChargeServiceTest {
    private CourierChargeService courierChargeService;
    private OfferService offerService;

    private DeliveryTimeService deliveryTimeService;
    @BeforeEach
    void setUp() {
        offerService = new OfferService();
        offerService.addOffers(Arrays.asList(
                new Offer("OFR001", 10d, 70, 200, 1, 199),
                new Offer("OFR002", 7d, 50, 150, 100, 250),
                new Offer("OFR003", 5d, 50, 250, 10, 150)));
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle(1, 70, 200, 200, 0d, 0d);
        Vehicle vehicle2 = new Vehicle(1, 70, 200, 200, 0d, 0d);
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        deliveryTimeService =  new DeliveryTimeService(vehicles);
        courierChargeService = new CourierChargeService(new CourierChargeRule(100d, 10d, 5d), offerService,  deliveryTimeService);

    }

    @Test
    void calculateCourierChargesForGivenPackage() {
        CourierPackage courierPackage1 = new CourierPackage("PKG1", 5, 5, "OFR001");
        CourierPackage courierPackage2 = new CourierPackage("PKG2", 15, 5, "OFR002");
        CourierPackage courierPackage3 = new CourierPackage("PKG3", 10, 100, "OFR003");
        CourierChargeResponse expectedResponseForPKG1 = new CourierChargeResponse("PKG1", 0d, 175d);
        CourierChargeResponse expectedResponseForPKG2 = new CourierChargeResponse("PKG2", 0d, 275d);
        CourierChargeResponse expectedResponseForPKG3 = new CourierChargeResponse("PKG3", 35d, 665d);
        List<CourierChargeResponse> expectedCourierChargeResponses = Arrays.asList(expectedResponseForPKG1, expectedResponseForPKG2, expectedResponseForPKG3);

        List<CourierChargeResponse> actualCourierChargeResponse = courierChargeService
                .calculateCourierCharges(Arrays.asList(courierPackage1, courierPackage2, courierPackage3));

        assertEquals(expectedCourierChargeResponses, actualCourierChargeResponse);

    }

    @Test
    void calculateCourierChargesWithDeliveryTimeForGivenPackage() {
        CourierPackage courierPackage1 = new CourierPackage("PKG1", 50, 30, "OFR001");
        CourierPackage courierPackage2 = new CourierPackage("PKG2", 75, 125, "OFFR0008");
        CourierPackage courierPackage3 = new CourierPackage("PKG3", 175, 100, "OFFR003");
        CourierPackage courierPackage4 = new CourierPackage("PKG4", 110, 60, "OFR002");
        CourierPackage courierPackage5 = new CourierPackage("PKG5", 155, 95);

        CourierChargeWithDeliveryTime expectedResponseForPKG1 = new CourierChargeWithDeliveryTime("PKG1", 0d, 750d, 3.98d);
        CourierChargeWithDeliveryTime expectedResponseForPKG2 = new CourierChargeWithDeliveryTime("PKG2", 0d,1475d, 1.78d);
        CourierChargeWithDeliveryTime expectedResponseForPKG3 = new CourierChargeWithDeliveryTime("PKG3", 0d, 2350d, 1.42d);
        CourierChargeWithDeliveryTime expectedResponseForPKG4 = new CourierChargeWithDeliveryTime("PKG4", 105d, 1395d, 0.85d);
        CourierChargeWithDeliveryTime expectedResponseForPKG5 = new CourierChargeWithDeliveryTime("PKG5", 0d, 2125d, 4.19d);

        List<CourierChargeWithDeliveryTime> expectedCourierChargeResponses = Arrays.asList(expectedResponseForPKG1,
                expectedResponseForPKG2, expectedResponseForPKG3, expectedResponseForPKG4, expectedResponseForPKG5);


        List<CourierChargeWithDeliveryTime> actualCourierChargeResponse = courierChargeService
                .calculateCourierChargesWithDeliveryTime(Arrays.asList(courierPackage1, courierPackage2, courierPackage3, courierPackage4, courierPackage5));

        assertEquals(expectedCourierChargeResponses, actualCourierChargeResponse);

    }
}
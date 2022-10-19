package org.courierService.service;

import org.courierService.model.Offer;
import org.courierService.model.CourierChargeResponse;
import org.courierService.model.CourierPackage;
import org.courierService.model.CourierChargeRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourierChargeServiceTest {
    private CourierChargeService courierChargeService;
    @BeforeEach
    void setUp() {
        OfferService offerService = new OfferService();
        offerService.addOffers(Arrays.asList(
                new Offer("OFR001", 10d, 70, 200, 1, 199),
                new Offer("OFR002", 7d, 50, 150, 100, 150),
                new Offer("OFR003", 5d, 50, 250, 10, 150)));

        courierChargeService = new CourierChargeService(new CourierChargeRule(100d, 10d, 5d), offerService);
    }

    @Test
    void calculateCourierChargesForGivenPackage() {
        CourierPackage courierPackage1 = new CourierPackage("PKG1", 5, 5, "OFR001");
        CourierPackage courierPackage2 = new CourierPackage("PKG2", 15, 5, "OFR002");
        CourierPackage courierPackage3 = new CourierPackage("PKG3", 10, 100, "OFR003");

        List<CourierChargeResponse> courierChargeRespons = courierChargeService
                .calculateCourierCharges(Arrays.asList(courierPackage1, courierPackage2, courierPackage3));
        CourierChargeResponse package1CourierChargeResponse = courierChargeRespons.get(0);
        CourierChargeResponse package2CourierChargeResponse = courierChargeRespons.get(1);
        CourierChargeResponse package3CourierChargeResponse = courierChargeRespons.get(2);

        assertEquals(0d, package1CourierChargeResponse.getDiscountApplied());
        assertEquals(175d, package1CourierChargeResponse.getTotalCost());
        assertEquals(0d, package2CourierChargeResponse.getDiscountApplied());
        assertEquals(275d, package2CourierChargeResponse.getTotalCost());
        assertEquals(35d, package3CourierChargeResponse.getDiscountApplied());
        assertEquals(665d, package3CourierChargeResponse.getTotalCost());

    }
}
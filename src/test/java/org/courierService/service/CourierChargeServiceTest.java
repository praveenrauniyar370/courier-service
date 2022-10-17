package org.courierService.service;

import org.courierService.model.Offer;
import org.courierService.model.PackageCourierChargeResponse;
import org.courierService.model.PackageRequest;
import org.courierService.model.PriceRule;
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

        courierChargeService = new CourierChargeService(new PriceRule(100d, 10d, 5d), offerService);
    }

    @Test
    void calculateCourierChargesForGivenPackage() {
        PackageRequest packageRequest1 = new PackageRequest("PKG1", 5, 5, "OFR001");
        PackageRequest packageRequest2 = new PackageRequest("PKG2", 15, 5, "OFR002");
        PackageRequest packageRequest3 = new PackageRequest("PKG3", 10, 100, "OFR003");

        List<PackageCourierChargeResponse> packageCourierChargeResponses = courierChargeService
                .calculateCourierCharges(Arrays.asList(packageRequest1, packageRequest2, packageRequest3));
        PackageCourierChargeResponse package1CourierChargeResponse = packageCourierChargeResponses.get(0);
        PackageCourierChargeResponse package2CourierChargeResponse = packageCourierChargeResponses.get(1);
        PackageCourierChargeResponse package3CourierChargeResponse = packageCourierChargeResponses.get(2);
        assertEquals(0d, package1CourierChargeResponse.getDiscountApplied());
        assertEquals(175d, package1CourierChargeResponse.getTotalCost());
        assertEquals(0d, package2CourierChargeResponse.getDiscountApplied());
        assertEquals(275d, package2CourierChargeResponse.getTotalCost());
        assertEquals(35d, package3CourierChargeResponse.getDiscountApplied());
        assertEquals(665d, package3CourierChargeResponse.getTotalCost());

    }
}
package org.courierService.service;

import org.courierService.model.Offer;
import org.courierService.model.PackageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OfferServiceTest {

    private OfferService offerService;
    @BeforeEach
    void setUp() {
        offerService = new OfferService();
        offerService.addOffers(Arrays.asList(
                new Offer("OFR001", 10d, 70, 200, 1, 199),
                new Offer("OFR002", 7d, 50, 150, 100, 150),
                new Offer("OFR003", 5d, 50, 250, 10, 150)));

    }

    @Test
    void shouldAddOffers() {
        offerService.addOffers(Collections.singletonList(new Offer("OFR004", 5d, 10, 100, 50, 700)));
        assertEquals(4, offerService.getOffers().size());
    }



    @Test
    void shouldGetAppliedDiscountPercentage() {
        PackageRequest packageRequest = new PackageRequest("PKG3", 10, 100, "OFR003");

        assertEquals(5d, offerService.getAppliedOfferDiscountPercentage(packageRequest));



    }
}
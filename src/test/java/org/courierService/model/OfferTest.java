package org.courierService.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    private Offer offer;

    @BeforeEach
    void setUp() {
        offer = new Offer("OFR001", 10d, 70, 200, 1, 199);

    }

    @Test
    void shouldReturnTrueWhenPackageWeightIsEligibleForOffer() {
        assertTrue(offer.isDistanceEligibleForOffer(76));

    }

    @Test
    void shouldReturnFalseWhenPackageWeightIsEligibleForOffer() {
        assertFalse(offer.isDistanceEligibleForOffer(69));

    }

    @Test
    void shouldReturnTrueWhenPackageDistanceIsEligibleForOffer() {
        assertTrue(offer.isDistanceEligibleForOffer(70));
    }

    @Test
    void shouldReturnFalseWhenPackageDistanceIsEligibleForOffer() {
        assertFalse(offer.isDistanceEligibleForOffer(201));

    }
}
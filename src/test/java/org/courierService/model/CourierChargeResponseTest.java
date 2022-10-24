package org.courierService.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierChargeResponseTest {

    @Test
    void shouldBeAbleToFormatDeliveryChargeResponse() {
        CourierChargeResponse pkg1 = new CourierChargeResponse("PKG1", 0d, 175d);
        assertEquals("Package Name=PKG1, Discount Applied=0.0, Total Cost=175.0", pkg1.toString());
    }
}
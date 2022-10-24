package org.courierService.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierChargeWithDeliveryTimeTest {

    @Test
    void shouldBeAbleToFormatCourierChargeResponseWithDeliveryTime() {
        CourierChargeWithDeliveryTime pkg1CourierChargeResponse = new CourierChargeWithDeliveryTime("PKG1", 0d, 175d, 3.57d);
        assertEquals("Package Name=PKG1, Discount Applied=0.0, Total Cost=175.0, Delivery Time=3.57", pkg1CourierChargeResponse.toString());

    }
}
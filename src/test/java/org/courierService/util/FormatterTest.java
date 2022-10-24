package org.courierService.util;

import org.courierService.model.CourierChargeResponse;
import org.courierService.model.CourierChargeWithDeliveryTime;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterTest {

    @Test
    void shouldFormatCourierChargeResponse() {
        CourierChargeResponse expectedResponseForPKG1 = new CourierChargeResponse("PKG1", 0d, 175d);
        CourierChargeResponse expectedResponseForPKG2 = new CourierChargeResponse("PKG2", 0d, 275d);
        List<CourierChargeResponse> courierChargeResponses = Arrays.asList(expectedResponseForPKG1, expectedResponseForPKG2);
        String expectedFormattedCourierChargeResponse = "\nCourier Charge Response - Package Name=PKG1, Discount Applied=0.0, Total Cost=175.0\n" +
                "Package Name=PKG2, Discount Applied=0.0, Total Cost=275.0\n";
         assertEquals(expectedFormattedCourierChargeResponse, Formatter.formatCourierChargeResponse(courierChargeResponses));
    }

    @Test
    void shouldFormatCourierChargeResponseWithDeliveryTime() {
        CourierChargeWithDeliveryTime expectedResponseForPKG1 = new CourierChargeWithDeliveryTime("PKG1", 0d, 175d, 3.57d);
        CourierChargeWithDeliveryTime expectedResponseForPKG2 = new CourierChargeWithDeliveryTime("PKG2", 0d, 275d, 4.56d);
        List<CourierChargeWithDeliveryTime> courierChargeResponses = Arrays.asList(expectedResponseForPKG1, expectedResponseForPKG2);
        String expectedFormattedCourierChargeResponse = "\nCourier Charge Response With Delivery Time- Package Name=PKG1, Discount Applied=0.0, Total Cost=175.0, Delivery Time=3.57\n" +
                "Package Name=PKG2, Discount Applied=0.0, Total Cost=275.0, Delivery Time=4.56\n";
        assertEquals(expectedFormattedCourierChargeResponse, Formatter.formatCourierChargeResponseWithDeliveryTime(courierChargeResponses));

    }
}
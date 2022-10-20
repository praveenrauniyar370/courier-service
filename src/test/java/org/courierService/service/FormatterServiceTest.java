package org.courierService.service;

import org.courierService.model.CourierChargeResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class FormatterServiceTest {

    @Test
    void shouldFormatCourierChargeResponse() {
        CourierChargeResponse expectedResponseForPKG1 = new CourierChargeResponse("PKG1", 0d, 175d);
        CourierChargeResponse expectedResponseForPKG2 = new CourierChargeResponse("PKG2", 0d, 275d);
        List<CourierChargeResponse> courierChargeResponses = Arrays.asList(expectedResponseForPKG1, expectedResponseForPKG2);
        String expectedFormattedCourierChargeResponse = "packageName=PKG1, discountApplied=0.0, totalCost=175.0\n" +
                "packageName=PKG2, discountApplied=0.0, totalCost=275.0";
        FormatterService.formatCourierChargeResponse(courierChargeResponses);
    }
}
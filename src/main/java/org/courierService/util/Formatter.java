package org.courierService.util;

import org.courierService.model.CourierChargeResponse;
import org.courierService.model.CourierChargeWithDeliveryTime;

import java.util.List;

public class Formatter {
    public static String formatCourierChargeResponse(List<CourierChargeResponse> courierChargeResponses){
        StringBuilder formattedResponse = new StringBuilder("\nCourier Charge Response - ");
        for (CourierChargeResponse courierChargeResponse : courierChargeResponses) {
            formattedResponse.append(courierChargeResponse.toString()).append("\n");
        }
        return formattedResponse.toString();
    }

    public static String formatCourierChargeResponseWithDeliveryTime(List<CourierChargeWithDeliveryTime> courierChargeWithDeliveryTimes){
        StringBuilder formattedResponse = new StringBuilder("\nCourier Charge Response With Delivery Time- ");
        for (CourierChargeWithDeliveryTime courierChargeWithDeliveryTime : courierChargeWithDeliveryTimes) {
            formattedResponse.append(courierChargeWithDeliveryTime.toString()).append("\n");
        }
        return formattedResponse.toString();
    }
}

package org.courierService.service;

import org.courierService.model.CourierChargeResponse;

import java.util.List;

public class FormatterService {
    public static String formatCourierChargeResponse(List<CourierChargeResponse> courierChargeResponses){
        StringBuilder formattedResponse = new StringBuilder();
        for (CourierChargeResponse courierChargeResponse : courierChargeResponses) {
            formattedResponse.append(courierChargeResponse.formatCourierChargeResponse()).append("\n");
        }
        return formattedResponse.toString();
    }
}

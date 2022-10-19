package org.courierService.service;

import org.courierService.model.CourierChargeResponse;
import org.courierService.model.CourierPackage;
import org.courierService.model.CourierChargeRule;

import java.util.List;
import java.util.stream.Collectors;

public class CourierChargeService {
    private final OfferService offerService;
    private final CourierChargeRule courierChargeRule;

    public CourierChargeService(CourierChargeRule courierChargeRule, OfferService offerService) {
        this.courierChargeRule = courierChargeRule;
        this.offerService = offerService;
    }



    public List<CourierChargeResponse> calculateCourierCharges(List<CourierPackage> courierPackages){
        return courierPackages.stream().map(this::calculateCourierChargePerPackage).collect(Collectors.toList());
    }

    private CourierChargeResponse calculateCourierChargePerPackage(CourierPackage courierPackage) {
        Double appliedOfferDiscount = offerService.getAppliedOfferDiscountPercentage(courierPackage);
        Double courierCharges = courierChargeRule.getBaseCost() + (courierPackage.getDistanceInKm() * courierChargeRule.getCostPerKilometer()) +
                (courierPackage.getWeight() * courierChargeRule.getCostPerKilogram());
        Double  discount = (courierCharges * appliedOfferDiscount)/ 100;
        return new CourierChargeResponse(courierPackage.getPackageCode(), discount, courierCharges - discount);
    }
}


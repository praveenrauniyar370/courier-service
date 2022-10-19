package org.courierService.service;

import org.courierService.model.CourierChargeResponse;
import org.courierService.model.CourierChargeRule;
import org.courierService.model.CourierChargeWithDeliveryTime;
import org.courierService.model.CourierPackage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourierChargeService {
    private final OfferService offerService;
    private final CourierChargeRule courierChargeRule;

    private final DeliveryTimeService deliveryTimeService;

    public CourierChargeService(CourierChargeRule courierChargeRule, OfferService offerService, DeliveryTimeService deliveryTimeService) {
        this.courierChargeRule = courierChargeRule;
        this.offerService = offerService;
        this.deliveryTimeService = deliveryTimeService;

    }

    public List<CourierChargeResponse> calculateCourierCharges(List<CourierPackage> courierPackages){
        return courierPackages.stream().map(this::calculateCourierChargePerPackage).collect(Collectors.toList());
    }

    public List<CourierChargeWithDeliveryTime> calculateCourierChargesWithDeliveryTime(List<CourierPackage> courierPackages){
        List<CourierChargeResponse> courierChargeResponses = courierPackages
                .stream().
                map(this::calculateCourierChargePerPackage).collect(Collectors.toList());
        Map<String, Double> packageDeliveryTimeMapping = this.deliveryTimeService.calculateDeliveryTimeForPackages(courierPackages);
        System.out.println(packageDeliveryTimeMapping);
        return courierChargeResponses.stream()
                .map((courierChargeResponse -> mapDeliveryTimeWithPackage(courierChargeResponse, packageDeliveryTimeMapping)))
                .collect(Collectors.toList());
    }

    private CourierChargeWithDeliveryTime mapDeliveryTimeWithPackage(CourierChargeResponse courierChargeResponse,
                                                                     Map<String, Double> packageDeliveryTimeMapping){
        return new CourierChargeWithDeliveryTime(courierChargeResponse.getPackageName(),
                courierChargeResponse.getDiscountApplied(),
                courierChargeResponse.getTotalCost(),
                packageDeliveryTimeMapping.get(courierChargeResponse.getPackageName()));
    }



    private CourierChargeResponse calculateCourierChargePerPackage(CourierPackage courierPackage) {
        Double appliedOfferDiscount = offerService.getAppliedOfferDiscountPercentage(courierPackage);
        Double courierCharges = courierChargeRule.getBaseCost() + (courierPackage.getDistanceInKm() * courierChargeRule.getCostPerKilometer()) +
                (courierPackage.getWeight() * courierChargeRule.getCostPerKilogram());
        Double  discount = (courierCharges * appliedOfferDiscount)/ 100;
        return new CourierChargeResponse(courierPackage.getPackageCode(), discount, courierCharges - discount);
    }
}


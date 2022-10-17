package org.courierService.service;

import org.courierService.model.PackageCourierChargeResponse;
import org.courierService.model.PackageRequest;
import org.courierService.model.PriceRule;

import java.util.List;
import java.util.stream.Collectors;

public class CourierChargeService {
    private final OfferService offerService;
    private PriceRule priceRule;

    public CourierChargeService(PriceRule priceRule, OfferService offerService) {
        this.priceRule = priceRule;
        this.offerService = offerService;
    }



    public List<PackageCourierChargeResponse> calculateCourierCharges(List<PackageRequest> packageRequests){
        return packageRequests.stream().map(this::calculateCourierChargePerPackage).collect(Collectors.toList());
    }

    private PackageCourierChargeResponse calculateCourierChargePerPackage(PackageRequest packageRequest) {
        Double appliedOfferDiscount = offerService.getAppliedOfferDiscountPercentage(packageRequest);
        Double courierCharges = priceRule.getBaseCost() + (packageRequest.getDistanceInKm() * priceRule.getCostPerKilometer()) +
                (packageRequest.getWeight() * priceRule.getCostPerKilogram());
        Double  discount = (courierCharges * appliedOfferDiscount)/ 100;
        return new PackageCourierChargeResponse(packageRequest.getPackageCode(), discount, courierCharges - discount);
    }
}


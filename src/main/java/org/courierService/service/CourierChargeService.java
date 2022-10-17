/*
package org.courierService.service;

import org.courierService.model.PackageCourierChargeResponse;
import org.courierService.model.PackageRequest;
import org.courierService.model.PriceRule;

import java.util.List;
import java.util.stream.Collectors;

public class CourierChargeService {
    private final OfferService offerService;
    private Double baseDeliveryCost;
    private PriceRule priceRule;

    public CourierChargeService(Double baseDeliveryCost, PriceRule priceRule, OfferService offerService) {
        this.baseDeliveryCost = baseDeliveryCost;
        this.priceRule = priceRule;
        this.offerService = offerService;
    }

    public List<PackageCourierChargeResponse> calculateCourierCharges(List<PackageRequest> packageRequests) throws Exception{
        return packageRequests.stream().map(this::calculateCourierChargePerPackage).collect(Collectors.toList());
    }

    private PackageCourierChargeResponse calculateCourierChargePerPackage(PackageRequest packageRequest) {
        Double appliedOfferDiscount = offerService.getAppliedOfferDiscount(packageRequest);
        Double courierCharges = baseDeliveryCost + (packageRequest.getDistanceInKm() * priceRule.getCostPerKilogram()) +
                (packageRequest.getWeight() * priceRule.getCostPerKilogram());



    }
}
*/

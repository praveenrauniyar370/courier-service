package org.courierService.service;

import org.courierService.model.Offer;
import org.courierService.model.CourierPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfferService {
    private final List<Offer> offers;

    public OfferService() {
        this.offers = new ArrayList<>();
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void addOffers(List<Offer> offers) {
        this.offers.addAll(offers);
    }


    private Offer getAppliedOfferCodeDetails(String appliedOfferCode) {
        List<Offer> appliedOffers = offers.stream().filter((offer -> offer.getOfferCode().equals(appliedOfferCode))).collect(Collectors.toList());
        if(appliedOffers.size() > 0){
            return appliedOffers.get(0);
        } else {
            System.out.println("Applied Offer Code is not valid");
        }
        return  null;
    }

    private boolean isAppliedOfferCodeValid(Offer offer, CourierPackage courierPackage){
        return offer.isDistanceEligibleForOffer(courierPackage.getDistanceInKm()) &&
                offer.isWeightEligibleForOffer(courierPackage.getWeight());

    }

    public Double getAppliedOfferDiscountPercentage(CourierPackage courierPackage) {
        Offer appliedOfferCodeDetails = getAppliedOfferCodeDetails(courierPackage.getCouponCode());

        if(appliedOfferCodeDetails != null && isAppliedOfferCodeValid(appliedOfferCodeDetails, courierPackage)){
            return appliedOfferCodeDetails.getDiscountPercentage();
        } else {
            System.out.println("Applied offer code not eligible of given distance or weight");
        }
        return 0d;
    }


}

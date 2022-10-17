package org.courierService.model;

public class Offer {
    private String offerCode;
    private Double discountPercentage;
    private Integer minimumEligibleDistance;
    private Integer maxEligibleDistance;
    private Integer minEligibleWeight;
    private Integer maxEligibleWeight;

    public Offer(String offerCode, Double discountPercentage, Integer minimumEligibleDistance, Integer maxEligibleDistance, Integer minEligibleWeight, Integer maxEligibleWeight) {
        this.offerCode = offerCode;
        this.discountPercentage = discountPercentage;
        this.minimumEligibleDistance = minimumEligibleDistance;
        this.maxEligibleDistance = maxEligibleDistance;
        this.minEligibleWeight = minEligibleWeight;
        this.maxEligibleWeight = maxEligibleWeight;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public Integer getMinimumEligibleDistance() {
        return minimumEligibleDistance;
    }

    public Integer getMaxEligibleDistance() {
        return maxEligibleDistance;
    }

    public Integer getMinEligibleWeight() {
        return minEligibleWeight;
    }

    public Integer getMaxEligibleWeight() {
        return maxEligibleWeight;
    }

    public boolean isWeightEligibleForOffer(Integer packageWeight){
        return (this.getMinEligibleWeight() <= packageWeight) &&
                (this.getMaxEligibleWeight() >= packageWeight);
    }

    public boolean isDistanceEligibleForOffer(Integer packageDistance){
        return (this.getMinimumEligibleDistance() <= packageDistance) &&
                (this.getMaxEligibleDistance() >= packageDistance);
    }
}

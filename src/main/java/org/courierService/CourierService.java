package org.courierService;

import org.courierService.model.CourierChargeResponse;
import org.courierService.model.CourierChargeRule;
import org.courierService.model.CourierPackage;
import org.courierService.model.Offer;
import org.courierService.service.CourierChargeService;
import org.courierService.service.FormatterService;
import org.courierService.service.OfferService;
import org.courierService.service.ScannerService;

import java.util.Arrays;
import java.util.List;

public class CourierService {
    public static void main(String[] args) {
        Double baseCost = ScannerService.scanBaseCost();
        int numberOfPackage = ScannerService.scanNumberOfPackage();
        List<CourierPackage> courierPackageList = ScannerService.scanCourierPackageList(numberOfPackage);
        CourierChargeService courierChargeService = new CourierChargeService(new CourierChargeRule(
                baseCost, 10d, 5d), createOfferService(),  null);
        List<CourierChargeResponse> courierChargeResponses = courierChargeService.calculateCourierCharges(courierPackageList);
        System.out.println(FormatterService.formatCourierChargeResponse(courierChargeResponses));
    }


    private static OfferService createOfferService(){
        OfferService offerService = new OfferService();
        offerService.addOffers(Arrays.asList(
                new Offer("OFR001", 10d, 70, 200, 1, 199),
                new Offer("OFR002", 7d, 50, 150, 100, 150),
                new Offer("OFR003", 5d, 50, 250, 10, 150)));
        return offerService;
    }
}
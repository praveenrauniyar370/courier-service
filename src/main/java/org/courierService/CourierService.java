package org.courierService;

import org.courierService.model.CourierChargeResponse;
import org.courierService.model.CourierChargeRule;
import org.courierService.model.CourierChargeWithDeliveryTime;
import org.courierService.model.CourierPackage;
import org.courierService.model.Offer;
import org.courierService.model.Vehicle;
import org.courierService.model.VehicleDetailsRequest;
import org.courierService.service.CourierChargeService;
import org.courierService.service.DeliveryTimeService;
import org.courierService.service.OfferService;
import org.courierService.service.ScannerService;
import org.courierService.util.Formatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourierService {
    public static void main(String[] args) {
        try {
            Double baseCost = ScannerService.scanBaseCost();
            int numberOfPackage = ScannerService.scanNumberOfPackage();
            List<CourierPackage> courierPackageList = ScannerService.scanCourierPackageList(numberOfPackage);
            boolean isDeliveryTime = ScannerService.scanIfDeliveryTimeNeedsToBeCalculated();
            if(!isDeliveryTime){
                calculateCourierChargeWithoutDeliveryTime(baseCost, courierPackageList);
            } else {
                VehicleDetailsRequest vehicleDetailsRequest = ScannerService.scanVehicleDetails();
                DeliveryTimeService deliveryTimeService = createDeliveryTimeService(vehicleDetailsRequest);
                calculateCourierChargeWithDeliveryTime(baseCost, courierPackageList, deliveryTimeService);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static DeliveryTimeService createDeliveryTimeService(VehicleDetailsRequest vehicleDetailsRequest) {
        List<Vehicle> vehicles = createVehicles(vehicleDetailsRequest);
        return new DeliveryTimeService(vehicles);
    }

    private static List<Vehicle> createVehicles(VehicleDetailsRequest vehicleDetailsRequest) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (int vehicleNumber = 0; vehicleNumber < vehicleDetailsRequest.getNumberOfVehicle(); vehicleNumber++) {
            vehicles.add(new Vehicle(vehicleNumber + 1, vehicleDetailsRequest.getMaxSpeedOfVehicle(), vehicleDetailsRequest.getMaxCarriableWeight(),
                    vehicleDetailsRequest.getMaxCarriableWeight(), 0, 0));
        }
        return vehicles;
    }

    private static void calculateCourierChargeWithoutDeliveryTime(Double baseCost, List<CourierPackage> courierPackageList) {
        CourierChargeService courierChargeService = new CourierChargeService(new CourierChargeRule(
                baseCost, 10d, 5d), createOfferService(),  null);
        List<CourierChargeResponse> courierChargeResponses = courierChargeService.calculateCourierCharges(courierPackageList);
        System.out.println(Formatter.formatCourierChargeResponse(courierChargeResponses));
    }

    private static void calculateCourierChargeWithDeliveryTime(Double baseCost, List<CourierPackage> courierPackageList, DeliveryTimeService deliveryTimeService) {
        CourierChargeService courierChargeService = new CourierChargeService(new CourierChargeRule(
                baseCost, 10d, 5d), createOfferService(),  deliveryTimeService);
        List<CourierChargeWithDeliveryTime> courierChargeResponses = courierChargeService.calculateCourierChargesWithDeliveryTime(courierPackageList);
        System.out.println(Formatter.formatCourierChargeResponseWithDeliveryTime(courierChargeResponses));
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
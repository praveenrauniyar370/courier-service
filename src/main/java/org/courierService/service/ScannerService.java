package org.courierService.service;

import org.courierService.Exception.InputTypeMismatchException;
import org.courierService.model.CourierPackage;
import org.courierService.model.VehicleDetailsRequest;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ScannerService {
    public static int scanNumberOfPackage() throws InputTypeMismatchException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter number of package");
        try {
            return in.nextInt();
        }catch (InputMismatchException e){
            throw new InputTypeMismatchException("Number of package should be only integer value");
        }

    }

    public static List<CourierPackage> scanCourierPackageList(int numberOfPackage) throws InputTypeMismatchException {
        System.out.println("Please enter package details in order of PackageId, Package Weight, Distance and Offer-Code by space separated");
        List<CourierPackage> courierPackageList = new ArrayList<>();
        for (int i = 0; i < numberOfPackage; i++) {
            System.out.println("Please enter " + (i+1) + " packageid, package weight, distance and offer_code");
            Scanner scan = new Scanner(System.in);

            String packageRequest = scan.nextLine();
            System.out.println(packageRequest);
            try {
                List<String> split = List.of(packageRequest.split(" "));
                courierPackageList.add(new CourierPackage(split.get(0), Integer.valueOf(split.get(1).trim()), Integer.valueOf(split.get(2).trim()), split.get(3)));
            } catch (Exception e){
                throw new InputTypeMismatchException("Package details should only contain 4 item by space separated in order by 1.Package name(String value)," +
                        "2.Package weight (integer value), 3.Package Distance (Integer value),4.Coupon code if applicable or input NA (String value)");
            }

        }
        return courierPackageList;
    }

    public static Double scanBaseCost() throws InputTypeMismatchException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter base cost");
        try {
            Double baseCost = in.nextDouble();
            System.out.println("Base cost will be - " + baseCost);
            return baseCost;
        } catch (InputMismatchException e){
            throw new InputTypeMismatchException("Base cost should be only integer or Double value");
        }
    }

    public static boolean scanIfDeliveryTimeNeedsToBeCalculated() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want to calculate delivery time enter Yes or No only");
        String s = scan.nextLine();
        return Objects.equals(s, "Yes");

    }

    public static VehicleDetailsRequest scanVehicleDetails() throws InputTypeMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of vehicle, max speed, max_carriable_weight by space separated for calculate Delivery Time");
        String vehicleDetails = scan.nextLine();
        List<String> vehicleDetailsInDetail = List.of(vehicleDetails.split(" "));
        try {
            return new VehicleDetailsRequest(Integer.valueOf(vehicleDetailsInDetail.get(0)), Double.valueOf(vehicleDetailsInDetail.get(1)),
                    Integer.valueOf(vehicleDetailsInDetail.get(2)));
        } catch (Exception e){
            throw new InputTypeMismatchException("Vehicle details should contain only 3 argument by space separated 1.Enter number of vehicle(Integer) " +
                    "2.max speed (Integer) 3.max_carriable_weight (Integer)");
        }
    }
}

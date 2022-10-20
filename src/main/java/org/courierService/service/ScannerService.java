package org.courierService.service;

import org.courierService.model.CourierPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerService {
    public static int scanNumberOfPackage() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter number of package");
        return in.nextInt();
    }

    public static List<CourierPackage> scanCourierPackageList(int numberOfPackage) {
        System.out.println("Please enter package details in order of PackageId, Package Weight, Distance and Offer Code by space separated");
        List<CourierPackage> courierPackageList = new ArrayList<>();
        for (int i = 0; i < numberOfPackage; i++) {
            System.out.println("Please enter " + (i+1) + " packageid, package weight, distance and offer_code");
            Scanner scan = new Scanner(System.in);

            String packageRequest = scan.nextLine();
            System.out.println(packageRequest);
            List<String> split = List.of(packageRequest.split(" "));
            courierPackageList.add(new CourierPackage(split.get(0), Integer.valueOf(split.get(1).trim()), Integer.valueOf(split.get(2).trim()), split.get(3)));
        }
        return courierPackageList;
    }

    public static Double scanBaseCost() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter base cost");
        Double baseCost = in.nextDouble();
        System.out.println("Base cost will be - " + baseCost);
        return baseCost;
    }
}

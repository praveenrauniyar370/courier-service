package org.courierService.model;

import java.util.ArrayList;
import java.util.List;

public class PackageMapping implements Comparable <PackageMapping>{
    private Integer weight;
    private List<PackageRequest> packageRequest;

    public PackageMapping(Integer weight) {
        this.weight =  weight;
        this.packageRequest = new ArrayList<>();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<PackageRequest> getPackageRequest() {
        return packageRequest;
    }

    @Override
    public int compareTo(PackageMapping packageMapping) {
        return packageMapping.weight - this.weight;
    }

    @Override
    public String toString() {
        return "PackageMapping{" +
                "weight=" + weight +
                ", packageRequest=" + packageRequest +
                '}';
    }
}

package com.nashtech.services;

import com.nashtech.models.Package;

import java.util.List;
import java.util.Map;

public interface PackageService {
    void createPackage(Package packages);

    List<Package> getPackages();

    Package findById(int id);

    Map<String, String> validatePackageLength(Package packages);

    Map<String, String> validatePackageWidth(Package packages);

    Map<String, String> validatePackageHeight(Package packages);

    Map<String, String> validatePackageSender(Package packages);

    Map<String, String> validatePackageSenderEmail(Package packages);

    Map<String, String> validatePackageCellID(Package packages);

    Map<String, String> validatePackageCustomerID(Package packages);

    Map<String, String> validatePackageCreateBy(Package packages);

    Map<String, String> validatePackageModifiedBy(Package packages);

    Map<String, String> validatePackageStatus(Package packages);

    Map<String, String> validatePackageType(Package packages);

    String getErrorMessage(Map<String, String> map);

    void deliverPackage(int id);

    List<Package> findPackage(int id, String email);
}

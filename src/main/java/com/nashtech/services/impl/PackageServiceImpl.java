package com.nashtech.services.impl;

import com.nashtech.dao.PackageDAO;
import com.nashtech.models.Package;
import com.nashtech.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageDAO packageDAO;

    public static boolean dataTypeCheck(String str, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    @Override
    public void createPackage(Package packages) {
        packageDAO.create(packages);
    }

    public List<Package> getPackages() {
        return packageDAO.findAll();
    }

    @Override
    public Package findById(int id) {
        return packageDAO.findById(id);
    }

    @Override
    public Map<String, String> validatePackageLength(Package packages) {
        Map<String, String> validatePackageLengthResult = new HashMap<>();
        double packageLength = packages.getLength();
        String packageLengthToString = packageLength + "";
        if (!dataTypeCheck(packageLengthToString, "^[0-9]+(.[0-9]+)?$")) {
            validatePackageLengthResult.put("packageLengthType", "Package length must be of type double");
        }
        if (packageLength <= 0) {
            validatePackageLengthResult.put("packageLengthGreaterThanZero", "Package length must be greater than 0");
        }
        return validatePackageLengthResult;
    }

    @Override
    public Map<String, String> validatePackageWidth(Package packages) {
        Map<String, String> validatePackageWidthResult = new HashMap<>();
        double packageWidth = packages.getWidth();
        String packageWidthToString = packageWidth + "";
        if (!dataTypeCheck(packageWidthToString, "^[0-9]+(.[0-9]+)?$")) {
            validatePackageWidthResult.put("packageWidthType", "Package width must be of type double");
        }
        if (packageWidth <= 0) {
            validatePackageWidthResult.put("packageWidthGreaterThanZero", "Package width must be greater than 0");
        }
        return validatePackageWidthResult;
    }

    @Override
    public Map<String, String> validatePackageHeight(Package packages) {
        Map<String, String> validatePackageHeightResult = new HashMap<>();
        double packageHeight = packages.getHeight();
        String packageHeightToString = packageHeight + "";
        if (!dataTypeCheck(packageHeightToString, "^[0-9]+(.[0-9]+)?$")) {
            validatePackageHeightResult.put("packageHeightType", "Package height must be of type double");
        }
        if (packageHeight <= 0) {
            validatePackageHeightResult.put("packageHeightGreaterThanZero", "Package height must be greater than 0");
        }
        return validatePackageHeightResult;
    }

    @Override
    public Map<String, String> validatePackageSender(Package packages) {
        Map<String, String> validatePackageSenderResult = new HashMap<>();
        String packageSender = packages.getSender();
        if (packageSender.length() < 1 || packageSender.length() > 50) {
            validatePackageSenderResult.put("packageSender", "Package sender length must be greater than 0 and less than 50");
        }
        if (packageSender.isEmpty()) {
            validatePackageSenderResult.put("packageSenderEmpty", "Package sender cannot be empty");
        }
        return validatePackageSenderResult;
    }

    @Override
    public Map<String, String> validatePackageSenderEmail(Package packages) {
        Map<String, String> validatePackageSenderEmailResult = new HashMap<>();
        String packageSenderEmail = packages.getSenderEmail();
        if (packageSenderEmail.length() < 1 || packageSenderEmail.length() > 50) {
            validatePackageSenderEmailResult.put("packageSenderEmailLength", "Package sender email length must be greater than 0 and less than 50");
        }
        if (packageSenderEmail.isEmpty()) {
            validatePackageSenderEmailResult.put("packageSenderEmailEmpty", "Package sender email cannot be empty");
        }
        return validatePackageSenderEmailResult;
    }

    @Override
    public Map<String, String> validatePackageCellID(Package packages) {
        Map<String, String> validatePackageCellIDResult = new HashMap<>();
        int packageCellID = packages.getCellId();
        String packageCellIDToString = packageCellID + "";
        if (!dataTypeCheck(packageCellIDToString, "^[0-9]+$")) {
            validatePackageCellIDResult.put("cabinetCellIDType", "Package cell ID must be of type integer");
        }
        if (packageCellID <= 0) {
            validatePackageCellIDResult.put("packageCellIDGreaterThanZero", "Package Cell ID must be greater than 0");
        }
        return validatePackageCellIDResult;
    }

    @Override
    public Map<String, String> validatePackageCustomerID(Package packages) {
        Map<String, String> validatePackageCustomerIDResult = new HashMap<>();
        int packageCustomerID = packages.getCustomerId();
        String packageCustomerIDToString = packageCustomerID + "";
        if (!dataTypeCheck(packageCustomerIDToString, "^[0-9]+$")) {
            validatePackageCustomerIDResult.put("packageCustomerIDType", "Package customer ID must be of type integer");
        }
        if (packageCustomerID <= 0) {
            validatePackageCustomerIDResult.put("packageCustomerIDGreaterThanZero", "Package customer ID must be greater than 0");
        }
        return validatePackageCustomerIDResult;
    }

    @Override
    public Map<String, String> validatePackageCreateBy(Package packages) {
        Map<String, String> validatePackageCreateByResult = new HashMap<>();
        String packageCreateBy = packages.getCreateBy();
        if (packageCreateBy.length() < 1 || packageCreateBy.length() > 50) {
            validatePackageCreateByResult.put("packageCreateByLength", "Package creator's name length must be greater than 0 and less than 50");
        }
        if (packageCreateBy.isEmpty()) {
            validatePackageCreateByResult.put("packageCreateByEmpty", "Package creator's name cannot be empty");
        }
        return validatePackageCreateByResult;
    }

    @Override
    public Map<String, String> validatePackageModifiedBy(Package packages) {
        Map<String, String> validatePackageModifiedByResult = new HashMap<>();
        String packageModifiedBy = packages.getModifiedBy();
        if (packageModifiedBy.length() < 1 || packageModifiedBy.length() > 50) {
            validatePackageModifiedByResult.put("packageModifiedByLength", "Package editor's name length must be greater than 0 and less than 50");
        }
        if (packageModifiedBy.isEmpty()) {
            validatePackageModifiedByResult.put("packageModifiedByEmpty", "Package editor's name cannot be empty");
        }
        return validatePackageModifiedByResult;
    }

    @Override
    public Map<String, String> validatePackageStatus(Package packages) {
        Map<String, String> validatePackageStatusResult = new HashMap<>();
        String packageStatus = packages.getStatus();
        if (packageStatus.length() < 1 || packageStatus.length() > 50) {
            validatePackageStatusResult.put("packageStatusLength", "Package status length must be greater than 0 and less than 50");
        }
        if (packageStatus.isEmpty()) {
            validatePackageStatusResult.put("packageStatusEmpty", "Package status cannot be empty");
        }
        return validatePackageStatusResult;
    }

    @Override
    public Map<String, String> validatePackageType(Package packages) {
        Map<String, String> validatePackageTypeResult = new HashMap<>();
        String packageType = packages.getType();
        if (packageType.length() < 1 || packageType.length() > 50) {
            validatePackageTypeResult.put("packageTypeLength", "Package type length must be greater than 0 and less than 50");
        }
        if (packageType.isEmpty()) {
            validatePackageTypeResult.put("packageTypeEmpty", "Package type cannot be empty");
        }
        return validatePackageTypeResult;
    }

    @Override
    public String getErrorMessage(Map<String, String> map) {
        String errorString = "";
        for (String i : map.values()) {
            errorString += i + ". ";
        }
        return errorString;
    }

    @Override
    public void deliverPackage(int id) {
        packageDAO.deliver(id);
    }

    @Override
    public List<Package> findPackage(int id, String email) {
        return packageDAO.findPackage(id, email);
    }
}

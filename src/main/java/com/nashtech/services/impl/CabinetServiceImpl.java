package com.nashtech.services.impl;

import com.nashtech.dao.CabinetDAO;
import com.nashtech.models.Cabinet;
import com.nashtech.services.CabinetService;
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
public class CabinetServiceImpl implements CabinetService {

    @Autowired
    private CabinetDAO cabinetDAO;

    public static boolean dataTypeCheck(String str, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    @Override
    public List<Cabinet> showCabinets() {
        return cabinetDAO.getCabinets();
    }

    @Override
    public void createCabinet(Cabinet cabinet) {
        cabinetDAO.create(cabinet);
    }

    @Override
    public void deleteCabinet(int id) {
        cabinetDAO.delete(id);
    }

    @Override
    public Map<String, String> validateCabinetName(Cabinet cabinet) {
        Map<String, String> validateCabinetNameResult = new HashMap<>();
        //Validate cabinetName
        String cabinetName = cabinet.getCabinetName();
        if (cabinetName.length() < 1 || cabinetName.length() > 50) {
            validateCabinetNameResult.put("cabinetNameLength", "Cabinet name length must be greater than 0 and less than 50");
        }
        if (cabinetName.isEmpty()) {
            validateCabinetNameResult.put("cabinetNameEmpty", "Cabinet name cannot be empty");
        }
        return validateCabinetNameResult;
    }

    @Override
    public Map<String, String> validateCabinetLength(Cabinet cabinet) {
        Map<String, String> validateCabinetLengthResult = new HashMap<>();
        //Validate cabinetLength
        double cabinetLength = cabinet.getLength();
        String cabinetLengthToString = cabinetLength + "";
        if (!dataTypeCheck(cabinetLengthToString, "^[0-9]+(.[0-9]+)?$")) {
            validateCabinetLengthResult.put("cabinetLengthType", "Cabinet length must be of type double");
        }
        if (cabinetLength <= 0) {
            validateCabinetLengthResult.put("cabinetLengthGreaterThanZero", "Cabinet length must be greater than 0");
        }
        return validateCabinetLengthResult;
    }

    @Override
    public Map<String, String> validateCabinetWidth(Cabinet cabinet) {
        Map<String, String> validateCabinetWidthResult = new HashMap<>();
        //Validate cabinetWidth
        double cabinetWidth = cabinet.getWidth();
        String cabinetWidthToString = cabinetWidth + "";
        if (!dataTypeCheck(cabinetWidthToString, "^[0-9]+(.[0-9]+)?$")) {
            validateCabinetWidthResult.put("cabinetWidthType", "Cabinet width must be of type double");
        }
        if (cabinetWidth <= 0) {
            validateCabinetWidthResult.put("cabinetWidthGreaterThanZero", "Cabinet width must be greater than 0");
        }
        return validateCabinetWidthResult;
    }

    @Override
    public Map<String, String> validateCabinetHeight(Cabinet cabinet) {
        Map<String, String> validateCabinetHeightResult = new HashMap<>();
        //Validate cabinetHeight
        double cabinetHeight = cabinet.getHeight();
        String cabinetHeightToString = cabinetHeight + "";
        if (!dataTypeCheck(cabinetHeightToString, "^[0-9]+(.[0-9]+)?$")) {
            validateCabinetHeightResult.put("cabinetHeightType", "Cabinet height must be of type double");
        }
        if (cabinetHeight <= 0) {
            validateCabinetHeightResult.put("cabinetHeightGreaterThanZero", "Cabinet height must be greater than 0");
        }
        return validateCabinetHeightResult;
    }

    @Override
    public Map<String, String> validateCabinetStorageID(Cabinet cabinet) {
        Map<String, String> validateCabinetStorageIDResult = new HashMap<>();
        //Validate cabinetCabinetStorageID
        int cabinetStorageID = cabinet.getStorageId();
        String cabinetStorageIDToString = cabinetStorageID + "";
        if (!dataTypeCheck(cabinetStorageIDToString, "^[0-9]+$")) {
            validateCabinetStorageIDResult.put("cabinetStorageIDType", "Cabinet storage ID must be of type integer");
        }
        if (cabinetStorageID <= 0) {
            validateCabinetStorageIDResult.put("cabinetStorageIDGreaterThanZero", "Cabinet StorageID must be greater than 0");
        }
        return validateCabinetStorageIDResult;
    }

    @Override
    public String getErrorMessage(Map<String, String> map) {
        String errorString = "";
        for (String i : map.values()) {
            errorString += i + ". ";
        }
        return errorString;
    }
}

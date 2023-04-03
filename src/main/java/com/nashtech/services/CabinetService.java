package com.nashtech.services;

import com.nashtech.models.Cabinet;

import java.util.List;
import java.util.Map;

public interface CabinetService {
    List<Cabinet> showCabinets();

    void createCabinet(Cabinet cabinet);

    void deleteCabinet(int id);

    Map<String, String> validateCabinetName(Cabinet cabinet);

    Map<String, String> validateCabinetLength(Cabinet cabinet);

    Map<String, String> validateCabinetWidth(Cabinet cabinet);

    Map<String, String> validateCabinetHeight(Cabinet cabinet);

    Map<String, String> validateCabinetStorageID(Cabinet cabinet);

    String getErrorMessage(Map<String, String> map);
}

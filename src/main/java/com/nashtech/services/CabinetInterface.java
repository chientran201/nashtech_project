package com.nashtech.services;

import com.nashtech.models.Cabinet;

import java.util.List;

public interface CabinetInterface {
    List<Cabinet> showCabinets();

    void createCabinet(Cabinet cabinet);
}

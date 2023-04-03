package com.nashtech.services;

import com.nashtech.models.Cell;

import java.util.List;

public interface CellService {

    List<Cell> getCell();

    void createCell(Cell cell);

    void deleteCell(int id);

}

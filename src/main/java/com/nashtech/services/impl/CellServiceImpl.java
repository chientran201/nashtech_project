package com.nashtech.services.impl;

import com.nashtech.dao.CellDAO;
import com.nashtech.models.Cell;
import com.nashtech.services.CellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class CellServiceImpl implements CellService {
    @Autowired
    private CellDAO cellDAO;

    @Override
    public List<Cell> getCell() {
        return cellDAO.findAll();
    }

    @Override
    public void createCell(Cell cell) {
        cellDAO.createCell(cell);
    }

    @Override
    public void deleteCell(int id) {
        cellDAO.deleteCell(id);
    }
}

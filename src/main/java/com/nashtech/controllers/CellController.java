package com.nashtech.controllers;

import com.nashtech.models.Cell;
import com.nashtech.services.impl.CellServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CellController {
    @Autowired
    private CellServiceImpl cellService;

    @GetMapping("/cell")
    public ModelAndView showAllCell(Model model) {
        ModelAndView modelAndView = new ModelAndView("/cell/cell-list");
        List<Cell> list = cellService.getCell();
        model.addAttribute("list", list);
        return modelAndView;

    }

    @GetMapping("/create-cell-form")
    public ModelAndView formCreateCell(@ModelAttribute("cell") Cell cell) {
        ModelAndView modelAndView = new ModelAndView("/cell/create-cell");
        modelAndView.addObject("cell", new Cell());
        return modelAndView;
    }

    @PostMapping("/create-cell")
    public String createCell(@ModelAttribute("cell") Cell cell, RedirectAttributes redirect) {
        cellService.createCell(cell);
        return ("redirect:/cell");
    }

    @GetMapping("/delete-cell/{id}")
    public String delete(@PathVariable int id) {
        cellService.deleteCell(id);
        return "redirect:/cell";
    }
}

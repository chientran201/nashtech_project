package com.nashtech.controllers;

import com.nashtech.models.Cabinet;
import com.nashtech.services.CabinetService;
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
import java.util.Map;

@Controller
public class CabinetController {

    @Autowired
    private CabinetService cabinetService;

    @GetMapping("/cabinets")
    public ModelAndView showAllCabinets(Model model) {
        ModelAndView modelAndView = new ModelAndView("/cabinet/config-cabinet-view");
        List<Cabinet> list = cabinetService.showCabinets();
        model.addAttribute("list", list);
        return modelAndView;
    }

    @GetMapping("/create-cabinet-form")
    public ModelAndView showCreateCabinetForm(@ModelAttribute("cabinet") Cabinet cabinet) {
        ModelAndView modelAndView = new ModelAndView("/cabinet/create-cabinet-view");
        modelAndView.addObject("cabinet", new Cabinet());
        return modelAndView;
    }

    @PostMapping("create-cabinet")
    public String createCabinet(@ModelAttribute("cabinet") Cabinet cabinet, Model model, RedirectAttributes redirect) {
        Map<String, String> validateCabinetNameResult = cabinetService.validateCabinetName(cabinet);
        Map<String, String> validateCabinetLengthResult = cabinetService.validateCabinetLength(cabinet);
        Map<String, String> validateCabinetWidthResult = cabinetService.validateCabinetWidth(cabinet);
        Map<String, String> validateCabinetHeightResult = cabinetService.validateCabinetHeight(cabinet);
        Map<String, String> validateCabinetStorageIDResult = cabinetService.validateCabinetStorageID(cabinet);

        if (validateCabinetNameResult.isEmpty() && validateCabinetLengthResult.isEmpty() &&
                validateCabinetWidthResult.isEmpty() && validateCabinetHeightResult.isEmpty() &&
                validateCabinetStorageIDResult.isEmpty()) {
            cabinetService.createCabinet(cabinet);
            return ("redirect:/cabinets");
        }

        model.addAttribute("cabinetNameValidate", cabinetService.getErrorMessage(validateCabinetNameResult));
        model.addAttribute("cabinetLengthValidate", cabinetService.getErrorMessage(validateCabinetLengthResult));
        model.addAttribute("cabinetWidthValidate", cabinetService.getErrorMessage(validateCabinetWidthResult));
        model.addAttribute("cabinetHeightValidate", cabinetService.getErrorMessage(validateCabinetHeightResult));
        model.addAttribute("cabinetStorageIDValidate", cabinetService.getErrorMessage(validateCabinetStorageIDResult));
        return "/cabinet/create-cabinet-error-view";
    }

    @GetMapping("delete-cabinet/{id}")
    public String deleteCabinet(@PathVariable int id) {
        cabinetService.deleteCabinet(id);
        return ("redirect:/cabinets");
    }


}

package com.nashtech.controllers;

import com.nashtech.models.Package;
import com.nashtech.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("/management")
    public String showManagementView() {
        return "/management/management-view";
    }

    @GetMapping("/packages")
    public ModelAndView showPackages(@RequestParam(name = "page", defaultValue = "1") int page) {
        ModelAndView mav = new ModelAndView("/package/package-list");

        // Retrieve package data from a data source
        List<Package> listPackages = packageService.getPackages();

        // Calculate total number of pages and starting index for current page
        int totalPages = (int) Math.ceil((double) listPackages.size() / 5);
        int startingIndex = (page - 1) * 5;

        // Retrieve packages for current page
        List<Package> packagesForPage = listPackages.stream()
                .skip(startingIndex)
                .limit(5)
                .collect(Collectors.toList());

        // Add packages, total pages, and current page to model
        mav.addObject("packages", packagesForPage);
        mav.addObject("totalPages", totalPages);
        mav.addObject("currentPage", page);

        return mav;
    }
    @GetMapping("/view-package/{id}")
    public ModelAndView packageDetail(@PathVariable("id") int id) {
        ModelAndView mav;
        Package packageInFor = packageService.findById(id);
        if (packageInFor != null) {
            mav = new ModelAndView("/package/view-package");
            mav.addObject("packages", packageInFor);
            return mav;
        } else {
            mav = new ModelAndView("error-404");
            return mav;
        }

    }

    @GetMapping("/create-package-form")
    public ModelAndView showCreatePackageForm() {
        ModelAndView modelAndView = new ModelAndView("/package/create-package-view");
        modelAndView.addObject("package", new Package());
        return modelAndView;
    }

    @PostMapping("/create-package")
    public String createPackage(@ModelAttribute("package") Package packages, Model model, RedirectAttributes redirect) {
        Map<String, String> validatePackageLengthResult = packageService.validatePackageLength(packages);
        Map<String, String> validatePackageWidthResult = packageService.validatePackageWidth(packages);
        Map<String, String> validatePackageHeightResult = packageService.validatePackageHeight(packages);
        Map<String, String> validatePackageSenderResult = packageService.validatePackageSender(packages);
        Map<String, String> validatePackageSenderEmailResult = packageService.validatePackageSenderEmail(packages);
        Map<String, String> validatePackageCellIDResult = packageService.validatePackageCellID(packages);
        Map<String, String> validatePackageCustomerIDResult = packageService.validatePackageCustomerID(packages);
        Map<String, String> validatePackageCreateByResult = packageService.validatePackageCreateBy(packages);
        Map<String, String> validatePackageModifiedByResult = packageService.validatePackageModifiedBy(packages);
        Map<String, String> validatePackageStatusResult = packageService.validatePackageStatus(packages);
        Map<String, String> validatePackageTypeResult = packageService.validatePackageType(packages);

        if (validatePackageLengthResult.isEmpty() && validatePackageWidthResult.isEmpty() && validatePackageHeightResult.isEmpty() &&
                validatePackageSenderResult.isEmpty() && validatePackageSenderEmailResult.isEmpty() && validatePackageCellIDResult.isEmpty() &&
                validatePackageCustomerIDResult.isEmpty() && validatePackageCreateByResult.isEmpty() && validatePackageModifiedByResult.isEmpty() &&
                validatePackageStatusResult.isEmpty() && validatePackageTypeResult.isEmpty()
        ) {
            packageService.createPackage(packages);
            return ("redirect:packages");
        }

        model.addAttribute("packageLengthValidate", packageService.getErrorMessage(validatePackageLengthResult));
        model.addAttribute("packageWidthValidate", packageService.getErrorMessage(validatePackageWidthResult));
        model.addAttribute("packageHeightValidate", packageService.getErrorMessage(validatePackageHeightResult));
        model.addAttribute("packageSenderValidate", packageService.getErrorMessage(validatePackageSenderResult));
        model.addAttribute("packageSenderEmailValidate", packageService.getErrorMessage(validatePackageSenderEmailResult));
        model.addAttribute("packageCellIDValidate", packageService.getErrorMessage(validatePackageCellIDResult));
        model.addAttribute("packageCustomerIDValidate", packageService.getErrorMessage(validatePackageCustomerIDResult));
        model.addAttribute("packageCreatedByValidate", packageService.getErrorMessage(validatePackageCreateByResult));
        model.addAttribute("packageModifiedByValidate", packageService.getErrorMessage(validatePackageModifiedByResult));
        model.addAttribute("packageStatusValidate", packageService.getErrorMessage(validatePackageStatusResult));
        model.addAttribute("packageTypeValidate", packageService.getErrorMessage(validatePackageTypeResult));
        return "/package/create-package-error-view";
    }

    @GetMapping("/show-deliver-package")
    public ModelAndView showDeliverPackageForm() {
        ModelAndView modelAndView = new ModelAndView("/package/deliver-package-view");
        return modelAndView;
    }

    @GetMapping("/get-info-package")
    public String findPackage(@RequestParam("id") int id, @RequestParam("email") String email, ModelMap modelMap) {
        List<Package> listPackage = packageService.findPackage(id, email);
        modelMap.addAttribute("listPackage", listPackage);
        return "/package/list-package-searched";
    }

    @GetMapping("/deliver-package/{id}")
    public String deliverPackage(@PathVariable int id) {
        packageService.deliverPackage(id);
        return ("redirect:/show-deliver-package");
    }
}

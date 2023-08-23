package com.finalexample.demo.controller;

import com.finalexample.demo.model.request.RegisterCarRequest;
import com.finalexample.demo.service.AdminService;
import com.finalexample.demo.service.CarListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController extends BaseController {

    private final AdminService adminService;

    private final CarListingService carListingService;

    @GetMapping("")
    public String getAdminView(Model model) {
        // TODO Add after implementing register-car
        model.addAttribute("carListings", carListingService.retrieveAllListings());
        return ok("admin-panel", model);
    }

    @GetMapping("/register-car")
    public String getRegisterCarView(Model model) {
        model.addAttribute("registerCarRequest", new RegisterCarRequest());
        return ok("register-car", model);
    }

    @PostMapping("/register-car")
    public String registerCar(@ModelAttribute("registerCarRequest") @Valid RegisterCarRequest registerCarRequest,
                              BindingResult bindingResult,
                              Model model) throws IOException {
        // If there are validation errors return existing page alongside errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerCarRequest", registerCarRequest);
            return ok("register-car", model);
        }

        adminService.registerNewCar(registerCarRequest);
        return ok("success", model);
    }

}

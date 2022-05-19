package com.example.report.controller;

import com.example.report.service.concretes.LabTechniciansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private LabTechniciansService labTechniciansService;

    @Autowired
    public HomeController(LabTechniciansService labTechniciansService) {
        this.labTechniciansService = labTechniciansService;
    }

    @RequestMapping("/")
    public String defaultUrl() {
        return "home";
    }

    @RequestMapping(value = "/home")
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "/about")
    public String aboutPage() {
        return "about";
    }


}

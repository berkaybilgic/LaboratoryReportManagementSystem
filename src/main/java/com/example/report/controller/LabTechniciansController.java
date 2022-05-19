package com.example.report.controller;

import com.example.report.entities.concretes.CustomUserDetails;
import com.example.report.entities.concretes.LabTechnicians;
import com.example.report.entities.concretes.Reports;
import com.example.report.service.concretes.LabTechniciansService;
import com.example.report.service.concretes.ReportsServices;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class LabTechniciansController {

    private LabTechniciansService labTechniciansService;
    private ReportsServices reportsServices;

    public LabTechniciansController(LabTechniciansService labTechniciansService, ReportsServices reportsServices) {
        this.reportsServices = reportsServices;
        this.labTechniciansService = labTechniciansService;
    }

    @RequestMapping(value = "/labtechicians")
    public String labTechiciansToList(@RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size, Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);
        Page<LabTechnicians> list = labTechniciansService.labTechniciansToList(currentPage, pageSize);
        model.addAttribute("labtechician", list);
        long pago = list.getTotalElements() / pageSize + 1;
        model.addAttribute("pago", pago);
        model.addAttribute("currentPage", currentPage);
        return "labTechiciansPage";

    }

    @GetMapping("/labtechicians/search")
    public String allreportSearch(Model model, @RequestParam("keyword") String string) {
        List<LabTechnicians> list = labTechniciansService.labTechnicansFindByNameAndLastName(string);
        model.addAttribute("labtechnician", list);
        return "labTechniciansSearch";
    }


    @RequestMapping(value = "/userpanel")
    public String userAllReportToList(Model model, @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails users = (CustomUserDetails) auth.getPrincipal();

        String labTechnicianUserName = users.getUsername();
        model.addAttribute("username", labTechnicianUserName);

        List<Reports> laborantInsertReports = reportsServices.findByLaborantInsertReports(users.getIdentityNumber());
        model.addAttribute("reports", laborantInsertReports);

        return "userPanel";


    }
}

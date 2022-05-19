package com.example.report.controller;

import com.example.report.entities.concretes.CustomUserDetails;
import com.example.report.entities.concretes.Images;
import com.example.report.entities.concretes.LabTechnicians;
import com.example.report.entities.concretes.Reports;
import com.example.report.service.concretes.ImagesService;
import com.example.report.service.concretes.LabTechniciansService;
import com.example.report.service.concretes.ReportsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ReportController {

    private ReportsServices reportsServices;
    private ImagesService imagesService;
    private LabTechniciansService labTechniciansService;

    @Autowired
    public ReportController(ReportsServices reportsServices, ImagesService imagesService, LabTechniciansService labTechniciansService) {
        this.reportsServices = reportsServices;
        this.imagesService = imagesService;
        this.labTechniciansService = labTechniciansService;
    }

    @RequestMapping(value = "/insertreport")
    public String insertReport(Model model) {
        Reports feedback = new Reports();
        model.addAttribute("report", feedback);
        return "insertReportPage";
    }

    @PostMapping("/insertreport")
    public String submitInsertReport(@ModelAttribute Reports reports, @RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails users = (CustomUserDetails) auth.getPrincipal();

        LabTechnicians labTechnicians = labTechniciansService.findByName(users.getUsername());
        try {
            UUID uuid = UUID.randomUUID();
            Images images = new Images();
            images.setId(uuid.toString());
            images.setContentType(multipartFile.getContentType());
            images.setImageByte(multipartFile.getBytes());

            reports.setTechniciansIdentificationNumber(labTechnicians);
            reports.setImagesId(images);
            imagesService.save(images);
            reportsServices.save(reports);
            redirectAttributes.addFlashAttribute("message", "Success");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-success");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("message", "Bu Kimlik Numaralı Hastanın Raporu Zaten Var");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger");
        }

        return "redirect:/insertreport";
    }

    @RequestMapping(value = "allreport")
    public String allReportPage(@RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size, Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        Page<Reports> list = reportsServices.reportToList(currentPage, pageSize);

        model.addAttribute("reports", list);
        long pago = (list.getTotalElements() / (pageSize + 1)) + 1;
        model.addAttribute("pago", pago);
        model.addAttribute("currentPage", currentPage);
        return "allReportsPage";
    }


    @GetMapping("/search")
    public String allReportSearch(Model model, @RequestParam("keyword") String string) {
        List<Reports> list = reportsServices.reportFindFirstNameAndLastNameIdentificationNumber(string);
        model.addAttribute("search", list);
        return "allReportSearch";
    }

    @RequestMapping(value = "allreport/{sort}")
    public String sortAllReportPage(@PathVariable(required = false) String sort,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size, Model model) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        if (sort.equals("asc")) {
            Page<Reports> list = reportsServices.reportSortAscToList(currentPage, pageSize);
            model.addAttribute("reports", list);
            long pago = (list.getTotalElements() / (pageSize + 1)) + 1;
            model.addAttribute("pago", pago);
            model.addAttribute("currentPage", currentPage);
        } else if (sort.equals("dsc")) {
            Page<Reports> list = reportsServices.reportSortDscToList(currentPage, pageSize);
            model.addAttribute("reports", list);
            long pago = (list.getTotalElements() / (pageSize + 1)) + 1;
            model.addAttribute("pago", pago);
            model.addAttribute("currentPage", currentPage);
        }
        return "allReportsPage";


    }


}

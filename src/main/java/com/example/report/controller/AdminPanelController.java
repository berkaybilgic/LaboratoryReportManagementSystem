package com.example.report.controller;

import com.example.report.entities.concretes.Images;
import com.example.report.entities.concretes.Reports;
import com.example.report.service.concretes.ImagesService;
import com.example.report.service.concretes.LabTechniciansService;
import com.example.report.service.concretes.ReportsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;

@Controller
public class AdminPanelController {

    private LabTechniciansService labTechniciansService;
    private ReportsServices reportsServices;
    private ImagesService imagesService;

    @Autowired
    public AdminPanelController(LabTechniciansService labTechniciansService, ReportsServices reportsServices,
                                ImagesService imagesService) {
        this.labTechniciansService = labTechniciansService;
        this.reportsServices = reportsServices;
        this.imagesService = imagesService;
    }

    @RequestMapping(value = "/adminpanel")
    public String adminAllReportToList(Model model, @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);
        Page<Reports> list = reportsServices.reportToList(currentPage, pageSize);

        model.addAttribute("reports", list);
        long pago = list.getTotalElements() / pageSize + 1;
        model.addAttribute("pago", pago);
        model.addAttribute("currentPage", currentPage);
        return "adminPanel";
    }

    @RequestMapping(value = "/update/report/{id}")
    public String update(Model model, @PathVariable(required = false) Integer id) {
        Reports feedback = new Reports();
        model.addAttribute("reports", feedback);

        Optional<Reports> report = reportsServices.findById(id);
        model.addAttribute("report", report.orElse(null));

        return "update";
    }


    @PostMapping("/update/report/{id}")
    public String updateSubmit(@ModelAttribute Reports report, @PathVariable(required = false) Integer id,
                               @RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {
        try {
            Optional<Reports> report2 = reportsServices.findById(id);
            Images images = report2.orElse(null).getImagesId();
            report.setFileNumber(id);
            report.setImagesId(images);
            report.setTechniciansIdentificationNumber(report2.orElse(null).getTechniciansIdentificationNumber());
            images.setContentType(multipartFile.getContentType());
            images.setImageByte(multipartFile.getBytes());
            imagesService.save(images);
            reportsServices.save(report);
            redirectAttributes.addFlashAttribute("message", "Success");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-success");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("message", "Bu Kimlik Numaralı Hastanın Raporu Zaten Var");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger");
        }
        return "redirect:/update/report/{id}";
    }

    @RequestMapping(value = "/delete/report/{id}")
    public String animalDelete(@PathVariable(required = false) Integer id, Model model) {

        reportsServices.deleteById(id);

        return "redirect:/adminpanel";
    }


}

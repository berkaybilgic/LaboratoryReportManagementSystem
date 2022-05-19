package com.example.report.controller;

import com.example.report.entities.concretes.LabTechnicians;
import com.example.report.service.concretes.LabTechniciansService;
import com.example.report.service.concretes.ReportsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserOperationsController {

    private LabTechniciansService labTechniciansService;
    private ReportsServices reportsServices;

    @Autowired
    public UserOperationsController(LabTechniciansService labTechniciansService, ReportsServices reportsServices) {
        this.labTechniciansService = labTechniciansService;
        this.reportsServices = reportsServices;
    }

    @RequestMapping(value = "/register")
    public String register(Model model) {
        LabTechnicians feedback = new LabTechnicians();
        model.addAttribute("user", feedback);
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute LabTechnicians labTechnicians, RedirectAttributes redirectAttributes) {

        LabTechnicians labTechnicians1 = labTechniciansService.findByIdentificationNumber(labTechnicians.getIdentificationNumber());

        try {
            if (labTechnicians1 == null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(labTechnicians.getPassword());
                labTechnicians.setPassword(encodedPassword);
                labTechniciansService.setUserRole(labTechnicians);
                labTechniciansService.save(labTechnicians);
                redirectAttributes.addFlashAttribute("message", "Success");
                redirectAttributes.addFlashAttribute("alertClass", "alert alert-success");

            } else if (labTechnicians1 != null) {
                redirectAttributes.addFlashAttribute("message", "Bu Kimlik Numaralı Kişi Zaten Bulunuyor");
                redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger");
            }
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("message", "Bu Emailde veya bu kullanıcı adında bir kullanıcı var");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger");
        }
        return "redirect:/register";
    }

    @RequestMapping(value = "/adminregister")
    public String adminRegister(Model model) {
        LabTechnicians feedback = new LabTechnicians();
        model.addAttribute("admin", feedback);
        return "adminRegister";
    }


    @PostMapping("/adminregister")
    public String adminRegisterSubmit(@ModelAttribute LabTechnicians labTechnicians, RedirectAttributes redirectAttributes) {

        LabTechnicians labTechnicians1 = labTechniciansService.findByIdentificationNumber(labTechnicians.getIdentificationNumber());
        try {
            if (labTechnicians1 == null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(labTechnicians.getPassword());
                labTechnicians.setPassword(encodedPassword);
                labTechniciansService.setAdminRole(labTechnicians);
                labTechniciansService.save(labTechnicians);
                redirectAttributes.addFlashAttribute("message", "Success");
                redirectAttributes.addFlashAttribute("alertClass", "alert alert-success");
            } else if (labTechnicians1 != null)
                redirectAttributes.addFlashAttribute("message", "Bu Kimlik Numaralı Kişi Zaten Bulunuyor");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("message", "Bu Emailde veya bu kullanıcı adında bir kullanıcı var");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger");
        }
        return "redirect:/adminregister";
    }

    @RequestMapping(value = "logon")
    public String Logon() {
        return "home";
    }

    @RequestMapping(value = "403")
    public String error403() {
        return "403Page";
    }


}

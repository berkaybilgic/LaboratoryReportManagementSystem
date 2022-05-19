package com.example.report.service.concretes;

import com.example.report.entities.concretes.LabTechnicians;
import com.example.report.repository.abstracts.LabTechniciansRepository;
import com.example.report.service.abstracts.LabTechniciansServiceInterFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTechniciansService implements LabTechniciansServiceInterFace {

    private LabTechniciansRepository labTechniciansRepository;

    @Autowired
    public LabTechniciansService(LabTechniciansRepository labTechniciansRepository) {
        this.labTechniciansRepository = labTechniciansRepository;
    }

    public void save(LabTechnicians labTechnicians) {
        labTechniciansRepository.save(labTechnicians);
    }

    public void setUserRole(LabTechnicians labTechnicians) {
        labTechnicians.setRoles("USER");
    }

    public void setAdminRole(LabTechnicians labTechnicians) {
        labTechnicians.setRoles("ADMIN");
    }

    public LabTechnicians findByName(String userName) {
        return labTechniciansRepository.findByUserName(userName);
    }

    public Page<LabTechnicians> labTechniciansToList(int currentPage,
                                                     int pageSize) {

        return labTechniciansRepository.findAll(PageRequest.of(currentPage - 1, pageSize, Sort.by(Sort.Direction.ASC, "firstName")));
    }

    public List<LabTechnicians> labTechnicansFindByNameAndLastName(String string) {
        return labTechniciansRepository.labTechnicansFindByNameAndLastName(string);
    }

    public LabTechnicians findByIdentificationNumber(String identity) {
        return labTechniciansRepository.findByIdentificationNumber(identity);
    }


}

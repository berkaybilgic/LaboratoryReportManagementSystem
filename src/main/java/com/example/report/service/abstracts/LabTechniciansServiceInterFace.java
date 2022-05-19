package com.example.report.service.abstracts;

import com.example.report.entities.concretes.LabTechnicians;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LabTechniciansServiceInterFace {

    void save(LabTechnicians labTechnicians);

    void setUserRole(LabTechnicians labTechnicians);

    LabTechnicians findByName(String userName);

    Page<LabTechnicians> labTechniciansToList(int currentPage, int pageSize);

    List<LabTechnicians> labTechnicansFindByNameAndLastName(String string);

    void setAdminRole(LabTechnicians labTechnicians);

    LabTechnicians findByIdentificationNumber(String identity);
}

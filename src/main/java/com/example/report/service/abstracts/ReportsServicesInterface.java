package com.example.report.service.abstracts;

import com.example.report.entities.concretes.Reports;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ReportsServicesInterface {


    void save(Reports reports);

    Page<Reports> reportToList(int currentPage, int pageSize);

    Optional<Reports> findById(Integer id);

    void deleteById(Integer id);

    List<Reports> reportFindFirstNameAndLastNameIdentificationNumber(String string);

    Page<Reports> reportSortAscToList(int currentPage, int pageSize);

    Page<Reports> reportSortDscToList(int currentPage, int pageSize);

    List<Reports> findByLaborantInsertReports(String identity);


}

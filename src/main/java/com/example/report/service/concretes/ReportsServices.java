package com.example.report.service.concretes;

import com.example.report.entities.concretes.Reports;
import com.example.report.repository.abstracts.ReportsRepository;
import com.example.report.service.abstracts.ReportsServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportsServices implements ReportsServicesInterface {

    private ReportsRepository reportsRepository;

    @Autowired
    public ReportsServices(ReportsRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    public void save(Reports reports) {
        reportsRepository.save(reports);
    }

    public Page<Reports> reportToList(int currentPage, int pageSize) {

        return reportsRepository.findAll(PageRequest.of(currentPage - 1, pageSize, Sort.by(Sort.Direction.ASC, "fileNumber")));
    }

    public Optional<Reports> findById(Integer id) {

        return reportsRepository.findById(id);
    }

    public void deleteById(Integer id) {
        reportsRepository.deleteById(id);
    }

    public List<Reports> reportFindFirstNameAndLastNameIdentificationNumber(String string) {
        return reportsRepository.reportFindFirstNameAndLastNameAndIdentificationNumber(string);
    }

    public Page<Reports> reportSortAscToList(int currentPage, int pageSize) {

        return reportsRepository.findAll(PageRequest.of(currentPage - 1, pageSize, Sort.by(Sort.Direction.ASC, "reportDate")));
    }

    public Page<Reports> reportSortDscToList(int currentPage, int pageSize) {

        return reportsRepository.findAll(PageRequest.of(currentPage - 1, pageSize, Sort.by(Sort.Direction.DESC, "reportDate")));
    }

    public List<Reports> findByLaborantInsertReports(String identity) {
        return reportsRepository.findByLaborantInsertReports(identity);
    }


}














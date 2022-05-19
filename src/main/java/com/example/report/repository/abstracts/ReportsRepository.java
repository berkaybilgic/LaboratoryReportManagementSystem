package com.example.report.repository.abstracts;


import com.example.report.entities.concretes.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReportsRepository extends JpaRepository<Reports, Integer> {


    @Query(
            value = "Select * from reports r where concat(r.patient_firstname,' ',r.patient_lastname) =?1 or r.patient_identification_number =?1",
            nativeQuery = true)
    List<Reports> reportFindFirstNameAndLastNameAndIdentificationNumber(String string);

    @Query("SELECT r from Reports r where r.techniciansIdentificationNumber.IdentificationNumber =?1")
    List<Reports> findByLaborantInsertReports(String Identity);


}

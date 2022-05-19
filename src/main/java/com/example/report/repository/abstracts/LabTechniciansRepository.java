package com.example.report.repository.abstracts;

import com.example.report.entities.concretes.LabTechnicians;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabTechniciansRepository extends JpaRepository<LabTechnicians, String> {

    @Query("SELECT u FROM LabTechnicians u WHERE u.userName = ?1")
    LabTechnicians findByUserName(String userName);

    @Query(
            value = "Select * from labtechnicians l where concat(l.firstname,' ',l.lastname) =?1",
            nativeQuery = true)
    List<LabTechnicians> labTechnicansFindByNameAndLastName(String string);

    @Query("SELECT l from LabTechnicians l where l.IdentificationNumber =?1 ")
    LabTechnicians findByIdentificationNumber(String Identity);


}

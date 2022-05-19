package com.example.report.entities.concretes;

import javax.persistence.*;

@Entity
@Table(name = "reports")
public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_number")
    private int fileNumber;

    @Column(name = "patient_firstname")
    private String patientFirstName;

    @Column(name = "patient_lastname")
    private String patientLastName;

    @Column(name = "patient_identification_number", unique = true)
    private String patientIdentificationNumber;

    @Column(name = "diagnostic_title")
    private String diagnosticTitle;

    @Column(name = "diagnostic_details")
    private String diagnosticDetails;

    @Column(name = "report_date")
    private String reportDate;

    @ManyToOne
    private LabTechnicians techniciansIdentificationNumber;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(unique = true)
    private Images imagesId;

    public Reports() {
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientIdentificationNumber() {
        return patientIdentificationNumber;
    }

    public void setPatientIdentificationNumber(String patientIdentificationNumber) {
        this.patientIdentificationNumber = patientIdentificationNumber;
    }

    public String getDiagnosticTitle() {
        return diagnosticTitle;
    }

    public void setDiagnosticTitle(String diagnosticTitle) {
        this.diagnosticTitle = diagnosticTitle;
    }

    public String getDiagnosticDetails() {
        return diagnosticDetails;
    }

    public void setDiagnosticDetails(String diagnosticDetails) {
        this.diagnosticDetails = diagnosticDetails;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public LabTechnicians getTechniciansIdentificationNumber() {
        return techniciansIdentificationNumber;
    }

    public void setTechniciansIdentificationNumber(LabTechnicians techniciansIdentificationNumber) {
        this.techniciansIdentificationNumber = techniciansIdentificationNumber;
    }

    public Images getImagesId() {
        return imagesId;
    }

    public void setImagesId(Images imagesId) {
        this.imagesId = imagesId;
    }
}

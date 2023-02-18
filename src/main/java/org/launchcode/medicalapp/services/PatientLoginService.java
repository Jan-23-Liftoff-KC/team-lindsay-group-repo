package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.PatientLoginDto;

import java.util.List;

public interface PatientLoginService {

    void addPatientLogin(PatientLoginDto patientLoginDto);

    List<String> patientLogin(PatientLoginDto patientLoginDto);

}

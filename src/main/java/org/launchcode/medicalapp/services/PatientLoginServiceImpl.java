package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.PatientLoginDto;
import org.launchcode.medicalapp.models.PatientLogin;
import org.launchcode.medicalapp.repositories.PatientLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientLoginServiceImpl implements PatientLoginService{

    @Autowired
    private PatientLoginRepository patientLoginRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public void addPatientLogin(PatientLoginDto patientLoginDto) {
        PatientLogin patientLogin = new PatientLogin();
        patientLoginRepository.save(patientLogin);
    }

    @Override
    public List<String> patientLogin(PatientLoginDto patientLoginDto) {
        List<String> response = new ArrayList<>();
        Optional<PatientLogin> patientLoginOptional = patientLoginRepository.findByPatientEquals(patientLoginDto.getPatientDto().getId());
        if(patientLoginOptional.isPresent()){
            if(passwordEncoder.matches(patientLoginDto.getPassword(), patientLoginOptional.get().getPassword())){
                response.add(String.valueOf(patientLoginOptional.get().getPatient().getId()));
            } else {
                response.add("Username or password incorrect");
            }
        } else {
            response.add("Username or password incorrect");
        }
        return response;
    }
}

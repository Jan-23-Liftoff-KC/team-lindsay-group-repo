package org.launchcode.medicalapp.controllers;

import org.jetbrains.annotations.NotNull;
import org.launchcode.medicalapp.dtos.PatientDto;
import org.launchcode.medicalapp.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //have a get URL getPatients that returns all patients and a get URL that gets doctors and returns all doctors.
    //It is patients for a given doctor.
    @GetMapping("/doctor/{doctorId}")
    public List<PatientDto> getPatientsByDoctor(@PathVariable Long doctorId) {
        return patientService.getAllPatientsByDoctorId(doctorId);
    }

    @PostMapping("/doctor/{doctorId}")
    public void addPatient(@RequestBody PatientDto patientDto, @PathVariable Long doctorId) {
        patientService.addPatient(patientDto, doctorId);
    }

    @PostMapping("/addpatient/doctor/{doctorId}")
    public String registerPatient(@RequestBody @NotNull PatientDto patientDto, @PathVariable Long doctorId) {
        String passHash = passwordEncoder.encode(patientDto.getPassword());
        patientDto.setPassword(passHash);
        return patientService.registerNewPatient(patientDto, doctorId);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatientById(@PathVariable Long patientId) {
        patientService.deletePatientById(patientId);
    }

    @PutMapping("/")
    public void updatePatient(@RequestBody PatientDto patientDto) {
        patientService.updatePatientById(patientDto);
    }

    @PutMapping("/{patientId}/update/diagnosis")
    public void updatePatientDiagnosis(@RequestBody PatientDto patientDto) {
        patientService.updatePatientDiagDetails(patientDto);
    }

    @GetMapping("/{patientId}")
    public Optional<PatientDto> getPatientById(@PathVariable Long patientId) {
        return patientService.getPatientById(patientId);
    }

    @PostMapping("/login")
    public List<String> patientLogin(@RequestBody PatientDto patientDto) {
        return patientService.patientLogin(patientDto);
    }
}
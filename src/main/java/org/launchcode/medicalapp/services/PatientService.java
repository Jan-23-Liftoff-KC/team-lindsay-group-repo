package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.PatientDto;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<PatientDto> getAllPatientsByDoctorId(Long doctorId);

    @Transactional
    void addPatient(PatientDto patientDto, Long doctorId);

    @Transactional
    void deletePatientById(Long patientId);

    @Transactional
    void updatePatientById(PatientDto patientDto);

    Optional<PatientDto> getPatientById(Long patientId);
}

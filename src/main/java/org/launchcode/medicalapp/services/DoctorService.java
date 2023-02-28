package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.DoctorDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    @Transactional
    List<String> addDoctor(DoctorDto doctorDto);

    List<String> doctorLogin(DoctorDto doctorDto);

    List<DoctorDto> getAllDoctors();

    Optional<DoctorDto> getDoctorById(Long doctorId);
}
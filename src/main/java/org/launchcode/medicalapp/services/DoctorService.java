package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.DoctorDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;

public interface DoctorService {
    @Transactional
    List<String> addDoctor(DoctorDto doctorDto);

    List<String> doctorLogin(DoctorDto doctorDto);

    List<DoctorDto> getAllDoctors();
}
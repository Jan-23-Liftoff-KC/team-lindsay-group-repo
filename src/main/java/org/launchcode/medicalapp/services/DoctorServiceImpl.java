package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.DoctorDto;
import org.launchcode.medicalapp.models.Doctor;
import org.launchcode.medicalapp.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    //@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addDoctor(DoctorDto doctorDto){
        List<String> response = new ArrayList<>();
        Doctor doctor = new Doctor(doctorDto);
        doctorRepository.saveAndFlush(doctor);
        response.add("http://localhost:8080/templates/login.html");
        return response;
    }

    @Override
    public List<String> doctorLogin(DoctorDto doctorDto){
        List<String> response = new ArrayList<>();
        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorName(doctorDto.getDoctorName());
        if(doctorOptional.isPresent()){
            if(passwordEncoder.matches(doctorDto.getPassword(), doctorOptional.get().getPassword())){
                response.add("http://localhost:8080/templates/home.html");
                response.add(String.valueOf(doctorOptional.get().getId()));
            } else {
                response.add("Doctor name or password incorrect");
            }
        } else {
            response.add("Doctor name or password incorrect");
        }
        return response;

    }

    public List<DoctorDto> getAllDoctors(){
        List<Doctor> doctors = this.doctorRepository.findAll();
        return doctors.stream().map(entity -> {
            return new DoctorDto(entity);
        }).collect(toList());
        //or .collect(Collectors.toList());
    }

    @Override
    public Optional<DoctorDto> getDoctorById(Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isPresent()){
            return Optional.of(new DoctorDto(doctorOptional.get()));
        }
        return Optional.empty();
    }
}

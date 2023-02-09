package org.launchcode.medicalapp.controllers;

import org.launchcode.medicalapp.dtos.DoctorDto;
import org.launchcode.medicalapp.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
   // @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public List<DoctorDto> getAllDoctors(){
        return doctorService.getAllDoctors();
    }
    @PostMapping("/register")
    public List<String> addDoctor(@RequestBody DoctorDto doctorDto){
        //String passHash = passwordEncoder.encode(doctorDto.getPassword());
        //doctorDto.setPassword(passHash);
        return doctorService.addDoctor(doctorDto);
    }

    @PostMapping("/login")
    public List<String> doctorLogin(@RequestBody DoctorDto doctorDto){
        return doctorService.doctorLogin(doctorDto);
    }
}
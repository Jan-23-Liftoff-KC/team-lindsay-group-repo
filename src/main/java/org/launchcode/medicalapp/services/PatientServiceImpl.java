package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.PatientDto;
import org.launchcode.medicalapp.models.Doctor;
import org.launchcode.medicalapp.models.Patient;
import org.launchcode.medicalapp.repositories.DoctorRepository;
import org.launchcode.medicalapp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<PatientDto> getAllPatientsByDoctorId(Long doctorId){
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isPresent()){
            List<Patient> patientList = patientRepository.findAllByDoctorEquals(doctorOptional.get());
            return patientList.stream().map(patient -> new PatientDto(patient)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addPatient(PatientDto patientDto, Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        Patient patient = new Patient(patientDto);
        doctorOptional.ifPresent(patient::setDoctor);
        patientRepository.saveAndFlush(patient);
    }

    @Override
    @Transactional
    public String registerNewPatient(PatientDto patientDto, Long doctorId) {
        Optional<Patient> patientOpt = patientRepository.findByEmail(patientDto.getEmail());
        if (patientOpt != null){
            return "Email already exist, Please enter a new email.";
        }
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        Patient patient = new Patient(patientDto);
        doctorOptional.ifPresent(patient::setDoctor);
        patientRepository.saveAndFlush(patient);
        return "SUCCESS";
    }

    @Override
    @Transactional
    public void deletePatientById(Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        patientOptional.ifPresent(patient -> patientRepository.delete(patient));
    }

    @Override
    @Transactional
    public void updatePatientById(PatientDto patientDto) {
        Optional<Patient> patientOptional = patientRepository.findById(patientDto.getId());
        patientOptional.ifPresent(patient -> {
            patient.setAge(patientDto.getAge());
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setDiagnosis(patientDto.getDiagnosis());
            patient.setPrescriptions(patientDto.getPrescriptions());
            patient.setDoctorNotes(patientDto.getDoctorNotes());
            patient.setEmail(patientDto.getEmail());
            patient.setPhoneNo(patientDto.getPhoneNo());
            patientRepository.saveAndFlush(patient);
        });
    }

    @Override
    @Transactional
    public void updatePatientDiagDetails(PatientDto patientDto) {
        Optional<Patient> patientOptional = patientRepository.findById(patientDto.getId());
        patientOptional.ifPresent(patient -> {
            patient.setDiagnosis(patientDto.getDiagnosis());
            patient.setPrescriptions(patientDto.getPrescriptions());
            patient.setDoctorNotes(patientDto.getDoctorNotes());
            patientRepository.saveAndFlush(patient);
        });
    }

    @Override
    public List<String> patientLogin(PatientDto patientDto){
        List<String> response = new ArrayList<>();
        Optional<Patient> patientOptional = patientRepository.findByEmail(patientDto.getEmail());
        if(patientOptional.isPresent()){
            if(passwordEncoder.matches(patientDto.getPassword(), patientOptional.get().getPassword())){
                response.add("http://localhost:8080/appointmentDetails.html");
                response.add(String.valueOf(patientOptional.get().getId()));
                response.add(String.valueOf(patientOptional.get().getDoctor().getId()));
                response.add(String.valueOf(patientOptional.get().getFirstName()));
                response.add(String.valueOf(patientOptional.get().getLastName()));
            } else {
                response.add("http://localhost:8080/patientLogin.html");
                response.add("username or password incorrect");
            }
        } else {
            response.add("username or password incorrect");
        }
        return response;
    }

    @Override
    public Optional<PatientDto> getPatientById(Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){
            return Optional.of(new PatientDto(patientOptional.get()));
        }
        return Optional.empty();
    }
}
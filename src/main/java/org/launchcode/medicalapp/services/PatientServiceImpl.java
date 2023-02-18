package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.PatientDto;
import org.launchcode.medicalapp.dtos.PatientLoginDto;
import org.launchcode.medicalapp.models.Doctor;
import org.launchcode.medicalapp.models.Patient;
import org.launchcode.medicalapp.models.PatientLogin;
import org.launchcode.medicalapp.repositories.DoctorRepository;
import org.launchcode.medicalapp.repositories.PatientLoginRepository;
import org.launchcode.medicalapp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientLoginRepository patientLoginRepository;

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
    public PatientDto addAndGetPatient(PatientDto patientDto, Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        Patient patient = new Patient(patientDto);
        doctorOptional.ifPresent(patient::setDoctor);
        patientRepository.saveAndFlush(patient);
        PatientDto patientDto1 = new PatientDto(patient);
        return patientDto1;
    }

    @Override
    public void registerPatient(PatientDto patientDto, Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        Patient patient = new Patient(patientDto);
        doctorOptional.ifPresent(patient::setDoctor);
        patientRepository.saveAndFlush(patient);

        PatientLoginDto patientLoginDto = new PatientLoginDto();
        patientLoginDto.setUserName(patientDto.getEmail());
        patientLoginDto.setPassword("pass1234");
        PatientLogin patientLogin = new PatientLogin(patientLoginDto);
        patientLoginRepository.saveAndFlush(patientLogin);

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
    public Optional<PatientDto> getPatientById(Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){
            return Optional.of(new PatientDto(patientOptional.get()));
        }
        return Optional.empty();
    }
}
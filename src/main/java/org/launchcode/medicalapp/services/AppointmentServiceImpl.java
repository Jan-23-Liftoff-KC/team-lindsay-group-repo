package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.AppointmentDto;
import org.launchcode.medicalapp.models.Appointment;
import org.launchcode.medicalapp.models.Doctor;
import org.launchcode.medicalapp.models.Patient;
import org.launchcode.medicalapp.repositories.AppointmentRepository;
import org.launchcode.medicalapp.repositories.DoctorRepository;
import org.launchcode.medicalapp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy h:mm a");
    Date d = new Date();

    @Override
    public List<AppointmentDto> getActiveAppointmentsByDoctorId(Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isPresent()){
            List<Appointment> appointmentList = appointmentRepository.findAllByDoctorEquals(doctorOptional.get());
            return appointmentList.stream().filter(x -> x.getStatus() == 1 && (x.getAppointmentDate().compareTo(d) == 0)).map(appointment -> new AppointmentDto(appointment)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<AppointmentDto> getActiveAppointmentsByPatientId(Long patientId) {
        Optional<Patient>  patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){

            List<Appointment> appointmentList = appointmentRepository.findAllByPatientEquals(patientOptional.get());
            return appointmentList.stream().filter(x -> x.getStatus() == 1 && (x.getAppointmentDate().compareTo(d) > 0)).map(appointment -> new AppointmentDto(appointment)).collect(Collectors.toList());

        }
        return Collections.emptyList();
    }

    @Override
    public AppointmentDto addAppointment(AppointmentDto appointmentDto, Long doctorId, Long patientId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        Appointment appointment = new Appointment(appointmentDto);
        appointment.setStatus(1);
        doctorOptional.ifPresent(appointment::setDoctor);
        patientOptional.ifPresent(appointment::setPatient);
        appointmentRepository.saveAndFlush(appointment);
        AppointmentDto appointmentDto1 = new AppointmentDto(appointment);
        return appointmentDto1;
    }

    @Override
    public void updateAppointmentById(AppointmentDto appointmentDto) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDto.getId());
        appointmentOptional.ifPresent(appointment -> {
            appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
            appointmentRepository.save(appointment);
        });
    }

    @Override
    @Transactional
    public void deleteAppointmentById(AppointmentDto appointmentDto) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDto.getId());
        appointmentOptional.ifPresent(appointment -> {
            appointment.setStatus(0);
            appointmentRepository.save(appointment);
        });
    }

    @Override
    public Optional<AppointmentDto> getAppointmentById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if (appointmentOptional.isPresent()){
            return Optional.of(new AppointmentDto(appointmentOptional.get()));
        }
        return Optional.empty();
    }
}

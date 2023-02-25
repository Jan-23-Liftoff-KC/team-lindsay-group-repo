package org.launchcode.medicalapp.services;

import org.launchcode.medicalapp.dtos.AppointmentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<AppointmentDto> getActiveAppointmentsByDoctorId(Long doctorId);

    List<AppointmentDto> getActiveAppointmentsByPatientId(Long patientId);

    @Transactional
    AppointmentDto addAppointment(AppointmentDto appointmentDto, Long doctorId, Long patientId);

    @Transactional
    void updateAppointmentById(AppointmentDto appointmentDto);

    @Transactional
    public void deleteAppointmentById(AppointmentDto appointmentDto);

    Optional<AppointmentDto> getAppointmentById(Long appointmentId);

}

package org.launchcode.medicalapp.controllers;

import org.launchcode.medicalapp.dtos.AppointmentDto;
import org.launchcode.medicalapp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    //get appointment details with patient id
    @GetMapping("/patient/{patientId}")
    public List<AppointmentDto> getAppointmentsByPatient(@PathVariable Long patientId){
        return appointmentService.getActiveAppointmentsByPatientId(patientId);
    }
    //get appointment details with doctor id
    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDto> getAppointmentsByDoctor(@PathVariable Long doctorId){
        return appointmentService.getActiveAppointmentsByDoctorId(doctorId);
    }

    //Add appointment details with patient_id and doctor_id
    @PostMapping("doctor/{doctorId}/patient/{patientId}")
    public AppointmentDto addAppointment(@Valid @RequestBody AppointmentDto appointmentDto, @PathVariable Long patientId, @PathVariable Long doctorId) {
        return  appointmentService.addAppointment(appointmentDto, doctorId, patientId);
    }

    //Edit or update appointment details with appointment_id as reference
    @PutMapping("/{appointmentId}")
    public void updateAppointment(@RequestBody AppointmentDto appointmentDto){
        appointmentService.updateAppointmentById(appointmentDto);
    }

    @DeleteMapping("/{appointmentId}")
    public void deleteAppointment(@RequestBody AppointmentDto appointmentDto){
        appointmentService.deleteAppointmentById(appointmentDto);
    }

}

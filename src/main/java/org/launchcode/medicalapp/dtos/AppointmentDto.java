package org.launchcode.medicalapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.launchcode.medicalapp.models.Appointment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
private long id;
private String patient;

//private String doctor;



public AppointmentDto (Appointment appointment){
    if (appointment.getId() != null){
        this.id = appointment.getId();
    }
    if  (appointment.getPatient() != null){
        this.patient = appointment.getPatient();
    }
}
}
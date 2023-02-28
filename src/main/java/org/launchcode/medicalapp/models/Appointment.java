package org.launchcode.medicalapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.launchcode.medicalapp.dtos.AppointmentDto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Appointments")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "MM/dd/yyyy h:mm a")
    private Date appointmentDate;

    // 0-Cancelled by patient, 1-Active, 2-Completed
    private Integer status;

    @ManyToOne
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne
    @JsonBackReference
    private Patient patient;

    public Appointment(AppointmentDto appointmentDto){
        if (appointmentDto.getAppointmentDate()!= null){
            this.appointmentDate = appointmentDto.getAppointmentDate();
        }
        if (appointmentDto.getStatus()!= null){
            this.status = appointmentDto.getStatus();
        }
    }
}

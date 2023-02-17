package org.launchcode.medicalapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.launchcode.medicalapp.dtos.AppointmentDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@Table(name = "Appointments")
//@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne
    @JsonBackReference
    private Patient patient;
    private Illness illness;
    private Date date;

    //private Appointment appointment;

    public Appointment(AppointmentDto appointmentDto){
//        if (appointmentDto.getDoctor() != null){
//            this.doctor = appointmentDto.getDoctor();
//        };
        if (appointmentDto.getPatient() != null){
            this.patient = appointmentDto.getPatient():
        }
        if (appointmentDto.getIllness() != null){
            this.illness = appointmentDto.getIllness();
        }
        if (appointmentDto.getDate() != null){
            this.date = appointmentDto.getDate();
        }
//        this.patient = patient;
//        this.illness = illness;
//        this.date = date;
    }

//    public Doctor getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(Doctor doctor) {
//        this.doctor = doctor;
//    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Illness getIllness() {
        return illness;
    }

    public void setIllness(Illness illness) {
        this.illness = illness;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}

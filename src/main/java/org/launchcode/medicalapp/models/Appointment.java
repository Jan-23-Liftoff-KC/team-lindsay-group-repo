package org.launchcode.medicalapp.models;

import java.util.ArrayList;
import java.util.Date;

public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private Illness illness;
    private Date date;

    private Appointment appointment;

    public Appointment(
            Doctor doctor, Patient patient, Illness illness, Date date, Appointment appointment
    ){
        this.doctor = doctor;
        this.patient = patient;
        this.illness = illness;
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}

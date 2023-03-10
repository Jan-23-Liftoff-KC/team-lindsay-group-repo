package org.launchcode.medicalapp.repositories;

import org.launchcode.medicalapp.models.Appointment;
import org.launchcode.medicalapp.models.Doctor;
import org.launchcode.medicalapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
        List<Appointment> findAllByPatientEquals(Patient patient);
        List<Appointment> findAllByDoctorEquals(Doctor doctor);
}

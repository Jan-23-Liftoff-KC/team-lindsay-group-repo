package org.launchcode.medicalapp.repositories;

import org.launchcode.medicalapp.models.Appointment;
import org.launchcode.medicalapp.models.Doctor;
import org.launchcode.medicalapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
        List<Appointment> findAllByPatientEquals(Patient patient);
        List<Appointment> findAllByDoctorEquals(Doctor doctor);
}

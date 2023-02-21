package org.launchcode.medicalapp.repositories;

import org.launchcode.medicalapp.models.Doctor;
import org.launchcode.medicalapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByDoctorEquals(Doctor doctor);
    Optional<Patient> findByEmail(String email);
}

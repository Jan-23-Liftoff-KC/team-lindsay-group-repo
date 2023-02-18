package org.launchcode.medicalapp.repositories;

import org.launchcode.medicalapp.models.PatientLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientLoginRepository extends JpaRepository <PatientLogin, Long>{
    Optional<PatientLogin> findByPatientEquals(Long patientId);
}

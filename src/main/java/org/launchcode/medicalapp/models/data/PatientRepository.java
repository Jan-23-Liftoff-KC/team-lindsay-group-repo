package org.launchcode.medicalapp.models.data;

import org.launchcode.medicalapp.models.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
}

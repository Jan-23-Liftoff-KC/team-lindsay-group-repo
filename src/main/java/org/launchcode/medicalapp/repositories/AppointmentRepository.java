package org.launchcode.medicalapp.repositories;

import org.launchcode.medicalapp.models.Doctor;
import org.launchcode.medicalapp.models.Patient;
import org.launchcode.medicalapp.models.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    List<Appointment> findAll(Appointment appointment);
}

package org.launchcode.medicalapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.launchcode.medicalapp.dtos.PatientDto;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "Patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Integer age;

    @Column(columnDefinition = "text")
    private String diagnosis;

    @Column(columnDefinition = "text")
    private String prescriptions;

    @Column(columnDefinition = "text")
    private String doctorNotes;

    @ManyToOne
    @JsonBackReference
    private Doctor doctor;

    public Patient(PatientDto patientDto){
        if (patientDto.getFirstName() != null){
            this.firstName = patientDto.getFirstName();
        }
        if (patientDto.getLastName() != null){
            this.lastName = patientDto.getLastName();
        }
        if (patientDto.getAge() != null){
            this.age = patientDto.getAge();
        }
        if (patientDto.getDiagnosis() != null){
            this.diagnosis = patientDto.getDiagnosis();
        }
        if (patientDto.getPrescriptions() != null){
            this.prescriptions = patientDto.getPrescriptions();
        }
        if (patientDto.getDoctorNotes() != null){
            this.doctorNotes = patientDto.getDoctorNotes();
        }
    }

}

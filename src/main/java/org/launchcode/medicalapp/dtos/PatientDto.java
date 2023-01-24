package org.launchcode.medicalapp.dtos;

import org.launchcode.medicalapp.entities.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto implements Serializable {
    private Long id;
    private String diagnosis;
    private String doctorNotes;
    private String prescriptions;
    private String firstName;
    private String lastName;
    private Integer age;
    private DoctorDto doctorDto;

    public PatientDto(Patient patient){
        if (patient.getId() != null){
            this.id = patient.getId();
        }
        if (patient.getDiagnosis() != null){
            this.diagnosis = patient.getDiagnosis();
        }
        if (patient.getDoctorNotes() != null){
            this.doctorNotes = patient.getDoctorNotes();
        }
        if (patient.getPrescriptions() != null){
            this.prescriptions = patient.getPrescriptions();
        }
        if (patient.getFirstName() != null){
            this.firstName = patient.getFirstName();
        }
        if (patient.getLastName() != null){
            this.lastName = patient.getLastName();
        }
        if (patient.getAge() != null){
            this.age = patient.getAge();
        }
        this.doctorDto = new DoctorDto();
        this.doctorDto.setDoctorname(patient.getDoctor().getDoctorname());
        this.doctorDto.setId(patient.getDoctor().getId());
    }
}

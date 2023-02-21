package org.launchcode.medicalapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.launchcode.medicalapp.dtos.PatientDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"email"})
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

    @Column
    //@Email(message = "please enter a valid email")
    private String email;

    @Column
    //@Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number should be Numeric and 10 digits")
    private String phoneNo;

    @Column
    private String password;

    @ManyToOne
    @JsonBackReference
    @NotNull
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @JsonManagedReference
    private Set<Appointment> appointmentSet = new HashSet<>();

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
        if (patientDto.getEmail() != null){
            this.email = patientDto.getEmail();
        }
        if (patientDto.getPhoneNo() != null){
            this.phoneNo = patientDto.getPhoneNo();
        }
        if (patientDto.getPassword() != null){
            this.password = patientDto.getPassword();
        }
    }

}



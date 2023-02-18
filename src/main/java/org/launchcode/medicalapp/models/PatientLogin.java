package org.launchcode.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.launchcode.medicalapp.dtos.PatientLoginDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient_login")
public class PatientLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    @OneToOne(mappedBy = "patientLogin")
    @NotNull
    private Patient patient;

    public PatientLogin(PatientLoginDto patientLoginDto){
        if (patientLoginDto.getUserName() != null){
            this.userName = patientLoginDto.getUserName();
        }
        if (patientLoginDto.getPassword() != null){
            this.password = patientLoginDto.getPassword();
        }
    }

}



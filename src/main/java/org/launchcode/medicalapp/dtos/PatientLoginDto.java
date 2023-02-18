package org.launchcode.medicalapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.launchcode.medicalapp.models.PatientLogin;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientLoginDto implements Serializable {

    private Long id;
    private String userName;

    private String password;

    private PatientDto patientDto;

    public PatientLoginDto(PatientLogin patientLogin) {
        if (patientLogin.getId() != null){
            this.id = patientLogin.getId();
        }
        if (patientLogin.getUserName() != null){
            this.userName = patientLogin.getUserName();
        }
        if (patientLogin.getPassword() != null) {
            this.userName = patientLogin.getPassword();
        }

        this.patientDto = new PatientDto();
        this.patientDto.setId(patientLogin.getPatient().getId());
    }
}

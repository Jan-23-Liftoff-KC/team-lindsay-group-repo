package org.launchcode.medicalapp.dtos;

import org.launchcode.medicalapp.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto implements Serializable {
    private Long id;
    private String doctorname;
    private String password;
    private Set<DoctorDto> doctorDtoSet = new HashSet<>();
//we transfer an object of data every time. We simply transfer that object instead of calling everything seperately.
    //this handles the "new" creation for you. It's a cleaner way to transfer objects/info and it's more secure.
    //It encrypts the passwords when you are sending those.
    //dtos help us create the object in a clean manner. The services help us access those objects.

    public DoctorDto(Doctor doctor) {
        if (doctor.getId() != null){
            this.id = doctor.getId();
        }
        if (doctor.getDoctorname() != null){
            this.doctorname = doctor.getDoctorname();
        }
        if (doctor.getPassword() != null){
            this.password = doctor.getPassword();
        }
    }
}

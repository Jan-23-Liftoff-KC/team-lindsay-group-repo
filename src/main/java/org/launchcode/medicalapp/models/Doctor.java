package org.launchcode.medicalapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.launchcode.medicalapp.dtos.DoctorDto;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String doctorName;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @JsonManagedReference
    private Set<Patient> patientSet = new HashSet<>();

    /*
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Patient> patientSet = new HashSet<>();*/

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @JsonManagedReference
    private Set<Appointment> appointmentSet = new HashSet<>();

    public Doctor(DoctorDto doctorDto){
        if (doctorDto.getDoctorName() != null){
            this.doctorName = doctorDto.getDoctorName();
        }
        if (doctorDto.getPassword() != null){
            this.password = doctorDto.getPassword();
        }
    }
}

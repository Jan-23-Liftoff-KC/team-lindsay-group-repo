package org.launchcode.medicalapp.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//@Entity
//@Table(name= "Doctors")

public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private Long id;

    @Column(unique = true)
    private String doctorname;

    @Column
    private String password;


}

package org.launchcode.medicalapp.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

}

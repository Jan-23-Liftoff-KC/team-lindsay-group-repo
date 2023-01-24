package org.launchcode.medicalapp.models;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.sql.Date;


@Entity
public class Patient extends AbstractEntity{
    @NotBlank(message = "First Name is required")
    private String firstName;
    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Past(message = "Patient Birth Date cannot be a future date")
    private Date dateOfBirth;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number should be Numeric and 10 digits")
    private String mobileNo;


    //@ManyToMany
    //private List<Provider> providers  = new ArrayList<>();

    //@OneToMany
    //private List<Availability> availability = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
/*
    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
        this.availability = availability;
    }

 */
}

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

//    private Date appointment;
//    private static final FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
//    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
//    String pattern = "EEE, dd MMM yyyy HH:mm:ss Z";

    //FastDateFormat fdf = FastDateFormat.getInstance(PATTERN, TimeZone.getTimeZone("UTC"));
    //When you put a date object in the app, you may have to do conversion and set
    //the fields individually b/c the date objects may not be compatible out of the box.
    //How to send FastDateFormat into a Date object that compatible with SQL or is there another
    //date format in Java that can be used instead of FastDateFormat but which covers
    //Year Month Day and Time (Which is also part of Date object)
    //How do you bring the Calendar from the frontend?
    //Can start with date then add time.

    //How to call an API from Java: If bringing in info...may be able to simply call it from
    //the JS side (frontend). We should not store any of that date. Do a fetch request.
    //If on the frontend you don't have to worry about API calls and you get back the information.
    //on front end you need to format and parse the info and display it nicely in a column/row, etc...

    //parsing of the date with UTC timestamp on the JS side to the Java side in the PatientDTO.
    //If JS generates timestamp then I should be able to use a datetime object which parses for you.
    //In java if you create dto object and add another field, if the type of that field is the
    //DateTime object, JSON should parse into the object without problems. Add the new property
    //to the patientDTO and the property of that property will be DateTime.
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

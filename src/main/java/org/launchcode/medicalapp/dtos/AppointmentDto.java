package org.launchcode.medicalapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.launchcode.medicalapp.models.Appointment;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto implements Serializable {
    private Long id;
    private Date appointmentDate;
    private Integer status;
    private DoctorDto doctorDto;
    private PatientDto patientDto;

    public AppointmentDto(Appointment appointment) {
        if (appointment.getId() != null) {
            this.id = appointment.getId();
        }
        if (appointment.getAppointmentDate() != null) {
            this.appointmentDate = appointment.getAppointmentDate();
        }
        if (appointment.getStatus() != null){
            this.status = appointment.getStatus();
        }
        this.doctorDto = new DoctorDto();
        this.doctorDto.setDoctorName(appointment.getDoctor().getDoctorName());
        this.doctorDto.setId(appointment.getDoctor().getId());

        this.patientDto = new PatientDto();
        this.patientDto.setId(appointment.getPatient().getId());
        this.patientDto.setFirstName(appointment.getPatient().getFirstName());
        this.patientDto.setLastName(appointment.getPatient().getLastName());

    }
}

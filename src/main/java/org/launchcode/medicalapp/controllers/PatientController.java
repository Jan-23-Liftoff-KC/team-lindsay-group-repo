package org.launchcode.medicalapp.controllers;

import org.launchcode.medicalapp.models.Patient;
import org.launchcode.medicalapp.models.data.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("scheduleAppointment")
    public String displayPatientScheduleForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/scheduleAppointment";
    }

    @PostMapping("scheduleAppointment")
    public String displayScheduleAppSuccessPage(@Valid @ModelAttribute Patient newPatient,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Patient Details");
            return "patient/scheduleAppointment";
        }
        patientRepository.save(newPatient);
        return "patient/scheduleConfirmation";
    }


}

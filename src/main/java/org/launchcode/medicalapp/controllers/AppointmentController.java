package org.launchcode.medicalapp.controllers;

import org.launchcode.medicalapp.models.Appointment;
import org.launchcode.medicalapp.models.AppointmentData;
import org.launchcode.medicalapp.repositories.AppointmentRepository;
import org.launchcode.medicalapp.repositories.DoctorRepository;
import org.launchcode.medicalapp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
//@RequestMapping("appointments")
//@RequestMapping(value = "appointments", method = {RequestMethod.GET, RequestMethod.POST})
public class AppointmentController {

    @Autowired
    private DoctorRepository DoctorRepository;
    @Autowired
    private PatientRepository PatientRepository;
    @Autowired
    private AppointmentRepository AppointmentRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

//    public AppointmentController () {
//
//        columnChoices.put("all", "All");
//        columnChoices.put("patient", "Patient");
//        columnChoices.put("doctors", "Doctors");
//        columnChoices.put("appointments", "Appointments");
//
//    }

    @GetMapping("appointments")
    public String appointment(Model model) {
        model.addAttribute("doctors", DoctorRepository.findAll());
        model.addAttribute("patients", PatientRepository.findAll());
        model.addAttribute("appointments", AppointmentRepository.findAll());
        return "appointments";
    }

    @RequestMapping("appointment/{appointmentId}")
    public String listAppointmentsByColumnAndValue(@PathVariable int appointmentId, Model model) {
//        Iterable<Appointment> appointments;
//////        if (column.toLowerCase().equals("all")){
//////            appointments = AppointmentRepository.findAll();
////            model.addAttribute("title", "All Appointments");
////        } else {
////            appointments = AppointmentData.findByColumnAndValue(column, value, AppointmentRepository.findAll());
////            model.addAttribute("title", "Appointments with " + columnChoices.get(column) + ": " + value);
////        }
//        model.addAttribute("appointments", appointments);

        return "appointment";
    }
}

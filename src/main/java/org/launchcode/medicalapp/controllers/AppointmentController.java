package org.launchcode.medicalapp.controllers;

import jdk.jfr.Category;
import org.launchcode.medicalapp.models.Appointment;
import org.launchcode.medicalapp.models.AppointmentData;
import org.launchcode.medicalapp.models.Patient;
import org.launchcode.medicalapp.repositories.AppointmentRepository;
import org.launchcode.medicalapp.repositories.DoctorRepository;
import org.launchcode.medicalapp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

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

    @GetMapping("appointments")
    public String appointment(Model model) {
        model.addAttribute("doctors", DoctorRepository.findAll());
        model.addAttribute("patients", PatientRepository.findAll());
        model.addAttribute("appointments", AppointmentRepository.findAll());
        return "appointments";
    }

    @GetMapping("appointment/{appointmentId}")
    public String listAppointmentsByColumnAndValue(@PathVariable long appointmentId, Model model) {
        Optional<Appointment> optAppointment = AppointmentRepository.findById(appointmentId);
        if (optAppointment.isPresent()) {
            Appointment appointment = (Appointment) optAppointment.get();
            model.addAttribute("appointments", appointment);
            model.addAttribute("patients", appointment.getPatient());
            return "appointment";
        } else {
            return "redirect:../";
        }

//        Iterable<Appointment> appointments;
//////        if (column.toLowerCase().equals("all")){
//////            appointments = AppointmentRepository.findAll();
////            model.addAttribute("title", "All Appointments");
////        } else {
////            appointments = AppointmentData.findByColumnAndValue(column, value, AppointmentRepository.findAll());
////            model.addAttribute("title", "Appointments with " + columnChoices.get(column) + ": " + value);
////        }
//        model.addAttribute("appointments", appointments);
    }
}

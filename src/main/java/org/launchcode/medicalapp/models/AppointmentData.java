package org.launchcode.medicalapp.models;

import java.util.ArrayList;

public class AppointmentData {

    public static ArrayList<Appointment> findByColumnAndValue(String column, String value, Iterable<Appointment> allAppointments) {

        ArrayList<Appointment> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Appointment>) allAppointments;
        }

        if (column.equals("all")){
            results = findByValue(value, allAppointments);
            return results;
        }
        for (Appointment appointment : allAppointments) {

            String aValue = getFieldValue(appointment, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(appointment);
            }
        }

        return results;
    }

    public static String getFieldValue(Appointment appointment, String fieldName){
        String theValue;
        if (fieldName.equals("patient")){
            theValue = appointment.getPatient().toString();
        } else {
            theValue = appointment.getDoctor().toString();
        }

        return theValue;
    }

    public static ArrayList<Appointment> findByValue(String value, Iterable<Appointment> allAppointments) {
        String lower_val = value.toLowerCase();

        ArrayList<Appointment> results = new ArrayList<>();

        for (Appointment appointment : allAppointments) {
            if (appointment.getPatient().toString().toLowerCase().contains(lower_val)) {
                results.add(appointment);
            } else if (appointment.getDoctor().toString().toLowerCase().contains(lower_val)) {
                results.add(appointment);
            } else if (appointment.toString().toLowerCase().contains(lower_val)) {
                results.add(appointment);
            }
        }
        return results;
    }


}


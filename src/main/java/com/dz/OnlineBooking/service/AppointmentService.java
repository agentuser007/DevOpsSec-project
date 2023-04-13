package com.dz.OnlineBooking.service;

import com.dz.OnlineBooking.Model.Appointment;
import com.dz.OnlineBooking.Repository.AppointmentRepository;
import com.dz.OnlineBooking.result.Result;
import com.dz.OnlineBooking.util.DataValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(String id) throws Exception {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Appointment id not found - " + id));
    }

    public Result createAppointment(Appointment appointment) {
        LocalDateTime start = appointment.getStart();
        LocalDateTime end = appointment.getEnd();
        String msg = "";
        DataValidation dataValidation = new DataValidation();

        //Check if the appointment is already booked
        List<Appointment> appointments = appointmentRepository.findByStartBetween(appointment.getStart(), appointment.getEnd());
        for (Appointment app : appointments) {
            if (app.getUserId().equals(appointment.getUserId())) {
                return new Result("404","You have already booked an appointment in this time slot");
            }
        }
        if("200".equals(dataValidation.appointmentValid(appointment).getCode())){
            return new Result("200", "success",appointmentRepository.save(appointment));
        }

        return dataValidation.appointmentValid(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) throws Exception {
        Appointment existingAppointment = appointmentRepository.findById(appointment.getId())
                .orElseThrow(() -> new Exception("Appointment id not found - " + appointment.getId()));

        existingAppointment.setUserId(appointment.getUserId());
        existingAppointment.setStart(appointment.getStart());
        existingAppointment.setEnd(appointment.getEnd());

        return appointmentRepository.save(existingAppointment);
    }

    public void deleteAppointment(String id) throws Exception {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Appointment" + id));

        appointmentRepository.delete(appointment);
    }
}

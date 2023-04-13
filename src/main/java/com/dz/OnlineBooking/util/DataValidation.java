package com.dz.OnlineBooking.util;

import com.dz.OnlineBooking.Model.Appointment;
import com.dz.OnlineBooking.result.Result;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class DataValidation {

    public boolean timeValid(LocalDateTime start, LocalDateTime end) {
        //appointment validation
        if (start.isBefore(LocalDateTime.now())) {
            return false;
        }
        if (start.isAfter(end)) {
            return false;
        }
        if (start.equals(end)) {
            return false;
        }
        if (start.getDayOfMonth() != end.getDayOfMonth()) {
            return false;
        }
        if (start.getHour() < 8 || end.getHour() > 17) {
            return false;
        }
        if (start.getMinute() != 0 || end.getMinute() != 0) {
            return false;
        }
        return true;
    }

    public Result appointmentValid(Appointment appointment) {
        //appointment validation
        if (appointment.getStart().isBefore(LocalDateTime.now())) {
            return new Result("404","Appointment time must be after today");
        }
        if (appointment.getStart().isAfter(appointment.getEnd())) {
            return new Result("404","Appointment start time must be before end time");
        }
        if (appointment.getStart().equals(appointment.getEnd())) {
            return new Result("404","Appointment start time must not be equal to end time");
        }
        if (appointment.getStart().getDayOfMonth() != appointment.getEnd().getDayOfMonth()) {
            return new Result("404","Appointment start time and end time must be in the same day");
        }
        if (appointment.getStart().getHour() < 8 || appointment.getEnd().getHour() > 17) {
            return new Result("404","Appointment time must be between 8:00 and 17:00");
        }
        if (appointment.getStart().getMinute() != 0 || appointment.getEnd().getMinute() != 0) {
            return new Result("404","Appointment time must be in the hour");
        }
        return new Result("200", "success", appointment);
    }

    public boolean userIdValid(String userId) {
        //user validation
        if (userId.length() < 6) {
            return false;
        }
        return true;
    }


    public boolean passwordValid(String password) {
        //password validation
        if (password.length() < 6) {
            return false;
        }
        return true;
    }
}

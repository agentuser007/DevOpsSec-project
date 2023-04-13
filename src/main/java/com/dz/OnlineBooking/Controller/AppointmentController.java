package com.dz.OnlineBooking.Controller;

import com.dz.OnlineBooking.Model.Appointment;
import com.dz.OnlineBooking.Model.LoginUser;
import com.dz.OnlineBooking.Repository.AppointmentRepository;
import com.dz.OnlineBooking.result.Result;
import com.dz.OnlineBooking.service.AppointmentService;
import com.dz.OnlineBooking.util.DataValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Api(tags = "00-Appointment", value = "AppointmentApi")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentService appointmentService;
    private DataValidation dataValidation = new DataValidation();


    @ApiOperation(value = "Get Appointments info by time", notes = "Get Appointments info by time  (2023-04-14T10:00:00Z)")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Result getAppointments(@RequestBody LocalDateTime start) {
       // LocalDateTime startTime = LocalDateTime.parse(start, formatter);
      //  LocalDateTime endTime = LocalDateTime.parse(end, formatter);
       // if(dataValidation.timeValid(start, end)){
        if(appointmentRepository.findByStart(start).isEmpty()){
            return new Result("400", "Invalid time");

        }
            return new Result("200", "success", appointmentRepository.findByStart(start));
       // }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ApiOperation(value = "create an appointment", notes = "create an appointment (yyyy-MM-ddTHH:mmZ)")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result createAppointment(@RequestBody Appointment appointment) {
        //LoginUser userDetails = (LoginUser) authentication.getPrincipal();

        //String userid = session.getAttribute("userId").toString();
        //appointment.setUserId(appointment.getUserId());

        return appointmentService.createAppointment(appointment);

    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ApiOperation(value = "get appointment by id", notes = "get appointment by id")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Result getAppointmentById(@RequestBody String id) {
        if(appointmentRepository.findById(id) .isEmpty()){
            return new Result("400", "Invalid appointment id", null);
        }
        return new Result("200", "success", appointmentRepository.findById(id));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ApiOperation(value = "get appointments by user id", notes = "get appointments by user id")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Result getAppointmentByUserId(@RequestBody String userId) {
        if(appointmentRepository.findByUserId(userId).isEmpty()){
            return new Result("400", "Invalid user id or user don't have any appointment", null);
        }
        return new Result("200", "success", appointmentRepository.findByUserId(userId));

    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ApiOperation(value = "update an appointment", notes = "update an appointment")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateAppointment(@RequestBody Appointment appointment) {
        if(appointmentRepository.existsById(appointment.getId()) == false){
            return new Result("400", "Invalid appointment id", null);
        }
        if(dataValidation.timeValid(appointment.getStart(), appointment.getEnd())){
            return new Result("400", "Invalid time", null);
        }

        return new Result("200", "success", appointmentRepository.save(appointment));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ApiOperation(value = "delete an appointment", notes = "delete an appointment")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteAppointment(@RequestBody String id) {
        appointmentRepository.deleteById(id);
    }
}

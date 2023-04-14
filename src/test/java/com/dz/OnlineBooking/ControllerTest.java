package com.dz.OnlineBooking;

import com.dz.OnlineBooking.Model.Appointment;
import com.dz.OnlineBooking.Repository.AppointmentRepository;
import com.dz.OnlineBooking.service.AppointmentService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;


    @Test
    public void testSaveAndGetAppointmentById() throws Exception {
        // Create a new appointment
        Appointment appointment = new Appointment();
        appointment.setTitle("New Appointment");
        appointment.setStart(LocalDateTime.now().plusDays(1));
        appointment.setEnd(LocalDateTime.now().plusDays(1).plusHours(1));
        appointmentRepository.save(appointment);

        // Call the controller method
        mockMvc.perform(MockMvcRequestBuilders.post("/appointments/get")
                .contentType(MediaType.APPLICATION_JSON)
                .content(appointment.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title", Matchers.is("New Appointment")));
    }

}

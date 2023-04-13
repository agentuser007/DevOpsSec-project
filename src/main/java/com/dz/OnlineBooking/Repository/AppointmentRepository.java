package com.dz.OnlineBooking.Repository;

import com.dz.OnlineBooking.Model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    @Query("{'start' : { $gte: ?0, $lte: ?1 } }")
    List<Appointment> findByStartBetween(LocalDateTime start, LocalDateTime end);
    List<Appointment> findByUserId(String userId);
    List<Appointment> findByTitle(String title);
    List<Appointment> findByStart(LocalDateTime start);

}
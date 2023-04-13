package com.dz.OnlineBooking.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document
@Data
public class Appointment {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String description;
    private String userId;
}
package com.dz.OnlineBooking.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
public class User{
    @MongoId
    private String id;
    private String username;
    private String password;
    private String email;

}
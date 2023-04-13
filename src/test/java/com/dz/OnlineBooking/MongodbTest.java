package com.dz.OnlineBooking;

import com.dz.OnlineBooking.Model.User;
import com.mongodb.client.MongoClient;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/application-context.xml")
public class MongodbTest {



    @Test
    void save() {
        LocalDateTime toDateTime = LocalDateTime.of(2014, 9, 9, 19, 46, 45);
        LocalDateTime fromDateTime = LocalDateTime.of(2014, 9, 16, 7, 45, 55);

        long hours = ChronoUnit.HOURS.between(toDateTime, fromDateTime);
        System.out.println(hours);
    }

    @Test
    void find() {
    }

}

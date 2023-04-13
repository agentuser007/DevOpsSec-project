package com.dz.OnlineBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class OnlineBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookingApplication.class, args);
	}

}

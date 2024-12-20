package com.revature.EmpRemSysP1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.revature.models") // This tells Spring to look in the models package for DB entities
public class EmpRemSysP1Application {

	public static void main(String[] args) {
		SpringApplication.run(EmpRemSysP1Application.class, args);
	}

}

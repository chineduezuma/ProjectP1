package com.revature.EmpRemSysP1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.models") // This tells Spring to look in the models package for DB entities
@ComponentScan("com.revature") // This tells Spring to look in com.revature for Beans  (stereotype annotations)
@EnableJpaRepositories("com.revature.DAOs") //This tells Spring to look in the DAOs package forJPA Repositories
public class EmpRemSysP1Application {

	public static void main(String[] args) {
		SpringApplication.run(EmpRemSysP1Application.class, args);
	}

}

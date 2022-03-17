package com.adilmx.spring_mvc_app;

import java.util.Date;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.adilmx.spring_mvc_app.entities.Contrat;
import com.adilmx.spring_mvc_app.repository.ContratRepo;

@SpringBootApplication
public class SpringMvcAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcAppApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(ContratRepo contratRepo) {
		return args -> {
			for (int i = 0; i < 20 ; i++) {
				contratRepo.save(new Contrat(null,"c"+i,new Date(),10+i*2000,"cl"+i,getRandomBoolean()));
			}
		};
	} 
	public boolean getRandomBoolean() {
	    Random random = new Random();
	    return random.nextBoolean();
	}

}

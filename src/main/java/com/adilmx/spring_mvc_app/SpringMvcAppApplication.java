package com.adilmx.spring_mvc_app;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.adilmx.spring_mvc_app.entities.Role;
import com.adilmx.spring_mvc_app.entities.User;
import com.adilmx.spring_mvc_app.repository.RoleRepo;
import com.adilmx.spring_mvc_app.repository.UserRepo;
import com.adilmx.spring_mvc_app.service.ContratService;
import com.adilmx.spring_mvc_app.service.RoleService;
import com.adilmx.spring_mvc_app.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.adilmx.spring_mvc_app.entities.Contrat;
import com.adilmx.spring_mvc_app.repository.ContratRepo;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringMvcAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcAppApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(ContratService contratService, UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
		return args -> {
			for (int i = 0; i < 20 ; i++) {
				contratService.save(new Contrat(null,"c"+i,new Date(),10+i*2000,"cl"+i,getRandomBoolean()));
			}
			Role role = roleService.save(new Role("ROLE_ADMIN"));
			User user = new User();
			user.getAuthorities().add(role);
			user.setUsername("root");
			user.setPassword(passwordEncoder.encode("root"));
			userService.save(user);
		};
	} 
	public boolean getRandomBoolean() {
	    Random random = new Random();
	    return random.nextBoolean();
	}

}

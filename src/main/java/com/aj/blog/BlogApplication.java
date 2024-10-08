package com.aj.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner  {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	
    @Bean
    ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
	
		
		
	}

   

}

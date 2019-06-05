package br.com.alluminox.apiponto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiPontoApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiPontoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return new ModelMapper();
	}
	
	/*
	@Bean(name = "bcrypt")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Call run method on interface CommandLineRunner");
	}


}

package br.com.alluminox.apiponto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ApiPontoApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiPontoApplication.class, args);
	}

	/*
	@Bean
	public Flyway flyway() {
		// baselineOnMigrate é caso eu tenha tabelas no banco de dados
		
		 *  Substituir por um app.properties
		 *  flyway.url: jdbc:postgresql://${db.host}/${db.name}
			flyway.user: MYUSER
			flyway.password: MYPWD
			flyway.baseline-on-migrate=true
		 
		Flyway flyWay = Flyway.configure()
				.baselineOnMigrate(true)
			.dataSource("jdbc:mysql://localhost:3306/pontosdb?useTimezone=true&serverTimezone=UTC&sslMode=DISABLED&allowPublicKeyRetrieval=true",
					"develloper", "devellopeR@123")
			.load();
		
		flyWay.migrate();
		return flyWay;
	}
	* */
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return new ModelMapper();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Call run method on interface CommandLineRunner");
	}


}

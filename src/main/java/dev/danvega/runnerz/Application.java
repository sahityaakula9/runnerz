package dev.danvega.runnerz;

import dev.danvega.runnerz.run.Location;
import dev.danvega.runnerz.run.Run;
import dev.danvega.runnerz.run.RunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Application {
	public static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//log.info("");  //@todo  logging documentation
	}
}

	/* Functional Interface is a interface with a single abstract method
	which can be used as a lambda expression
	 */
	/*@Bean
	CommandLineRunner runner(RunRepository runRepository) {
		return args -> {
			Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 10, Location.OUTDOOR);
			runRepository.create(run);
		};
*/



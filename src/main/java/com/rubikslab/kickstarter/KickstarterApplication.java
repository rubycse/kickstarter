package com.rubikslab.kickstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
@PropertySource(value = "file:${KICKSTARTER_PROP_LOC}")
public class KickstarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(KickstarterApplication.class, args);
	}
}

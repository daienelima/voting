package com.cooperative.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CooperativeVotingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CooperativeVotingServiceApplication.class, args);
	}

}

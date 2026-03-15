package com.cooperative.voting;

import com.cooperative.voting.infrastructure.config.KafkaTopicsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(KafkaTopicsProperties.class)
public class CooperativeVotingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CooperativeVotingServiceApplication.class, args);
	}

}

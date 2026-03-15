package com.cooperative.voting.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.topics")
public class KafkaTopicsProperties {

    private String sessaoEncerrada;

    public KafkaTopicsProperties(String sessaoEncerrada) {
        this.sessaoEncerrada = sessaoEncerrada;
    }

    public String getSessaoEncerrada() {
        return sessaoEncerrada;
    }

}
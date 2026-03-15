package com.cooperative.voting.infrastructure.adapter.out.messaging;

import com.cooperative.voting.application.event.SessaoEncerradaEvent;
import com.cooperative.voting.infrastructure.config.KafkaTopicsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SessaoEventPublisher {

    private final KafkaTemplate<String, Object> kafka;
    private final KafkaTopicsProperties kafkaTopicsProperties;

    public SessaoEventPublisher(KafkaTemplate<String, Object> kafka, KafkaTopicsProperties kafkaTopicsProperties) {
        this.kafka = kafka;
        this.kafkaTopicsProperties = kafkaTopicsProperties;
    }

    public void publicar(SessaoEncerradaEvent event) {

        kafka.send(kafkaTopicsProperties.getSessaoEncerrada(), event.sessaoId().toString(), event);

    }

}
package com.cooperative.voting.infrastructure.adapter.out.messaging;

import com.cooperative.voting.application.event.SessaoEncerradaEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SessaoEventPublisher {

    private final KafkaTemplate<String, Object> kafka;

    public SessaoEventPublisher(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void publicar(SessaoEncerradaEvent event) {

        kafka.send("sessao-encerrada", event);

    }

}
package com.cooperative.voting.infrastructure.scheduler;

import com.cooperative.voting.infrastructure.adapter.out.persistence.entity.OutboxEventEntity;
import com.cooperative.voting.infrastructure.adapter.out.persistence.repository.OutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OutboxPublisherScheduler {

    private final OutboxRepository repository;
    private final KafkaTemplate<String, Object> kafka;
    private final ObjectMapper mapper;

    public OutboxPublisherScheduler(
            OutboxRepository repository,
            KafkaTemplate<String, Object> kafka,
            ObjectMapper mapper) {

        this.repository = repository;
        this.kafka = kafka;
        this.mapper = mapper;
    }

    @Scheduled(fixedDelay = 60000)
    public void publicarEventos() {

        List<OutboxEventEntity> eventos =
                repository.findTop50ByProcessedFalseOrderByCreatedAt();

        log.info("Publicando {} eventos do outbox", eventos.size());

        for (OutboxEventEntity evento : eventos) {

            kafka.send("sessao-encerrada", evento.getPayload());

            evento.setProcessed(true);

            repository.save(evento);
        }
    }
}
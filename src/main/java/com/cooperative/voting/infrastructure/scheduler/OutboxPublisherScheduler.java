package com.cooperative.voting.infrastructure.scheduler;

import com.cooperative.voting.application.event.SessaoEncerradaEvent;
import com.cooperative.voting.infrastructure.adapter.out.messaging.SessaoEventPublisher;
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
    private final SessaoEventPublisher publisher;
    private final ObjectMapper mapper;

    public OutboxPublisherScheduler(OutboxRepository repository, SessaoEventPublisher publisher, ObjectMapper mapper) {
        this.repository = repository;
        this.publisher = publisher;
        this.mapper = mapper;
    }


    @Scheduled(fixedDelay = 60000)
    public void publicarEventos() {

        List<OutboxEventEntity> eventos =
                repository.findTop50ByProcessedFalseOrderByCreatedAt();

        log.info("Publicando {} eventos do outbox", eventos.size());

        for (OutboxEventEntity evento : eventos) {

            try {
                log.debug("Enviando evento para Kafka: {}", evento.getId());

                SessaoEncerradaEvent event =
                        mapper.convertValue(evento.getPayload(), SessaoEncerradaEvent.class);

                publisher.publicar(event);
                evento.setProcessed(true);
                repository.save(evento);

                log.debug("Evento {} processado com sucesso", evento.getId());

            } catch (Exception e) {
                log.error("Erro ao publicar evento {}: {}", evento.getId(), e.getMessage(), e);
            }
        }
    }
}
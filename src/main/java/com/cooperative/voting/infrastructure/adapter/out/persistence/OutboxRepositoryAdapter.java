package com.cooperative.voting.infrastructure.adapter.out.persistence;

import com.cooperative.voting.domain.port.out.OutboxEventPort;
import com.cooperative.voting.infrastructure.adapter.out.persistence.entity.OutboxEventEntity;
import com.cooperative.voting.infrastructure.adapter.out.persistence.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class OutboxRepositoryAdapter implements OutboxEventPort {

    private final OutboxRepository repository;
    private final ObjectMapper mapper;

    public OutboxRepositoryAdapter(
            OutboxRepository repository,
            ObjectMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void salvarEvento(Object event) {

        try {

            OutboxEventEntity entity = new OutboxEventEntity(
                    UUID.randomUUID(),
                    event.getClass().getSimpleName(),
                    mapper.writeValueAsString(event),
                    OffsetDateTime.now(),
                    false
            );

            repository.save(entity);

        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);

        }
    }
}
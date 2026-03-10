package com.cooperative.voting.infrastructure.adapter.out.persistence.repository;

import com.cooperative.voting.infrastructure.adapter.out.persistence.entity.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxRepository
        extends JpaRepository<OutboxEventEntity, UUID> {

    List<OutboxEventEntity> findTop50ByProcessedFalseOrderByCreatedAt();

}
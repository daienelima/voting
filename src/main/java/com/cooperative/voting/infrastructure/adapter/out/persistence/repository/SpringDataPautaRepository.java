package com.cooperative.voting.infrastructure.adapter.out.persistence.repository;

import com.cooperative.voting.infrastructure.adapter.out.persistence.entity.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPautaRepository extends JpaRepository<PautaEntity, UUID> {
}

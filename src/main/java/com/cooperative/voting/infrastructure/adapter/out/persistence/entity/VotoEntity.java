package com.cooperative.voting.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "voto",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_voto_sessao_associado",
            columnNames = {"sessao_id", "associado_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoEntity {

    @Id
    private UUID id;

    @Column(name = "sessao_id", nullable = false)
    private UUID sessaoId;

    @Column(name = "associado_id", nullable = false)
    private String associadoId;

    @Column(nullable = false)
    private String escolha;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

}

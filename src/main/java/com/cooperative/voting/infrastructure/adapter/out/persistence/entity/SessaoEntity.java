package com.cooperative.voting.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "sessao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoEntity {

    @Id
    private UUID id;

    @Column(name = "pauta_id", nullable = false)
    private UUID pautaId;

    @Column(name = "data_abertura", nullable = false)
    private OffsetDateTime dataAbertura;

    @Column(name = "data_fechamento", nullable = false)
    private OffsetDateTime dataFechamento;

    @Column(name = "envento_encrramento_gerado", nullable = false)
    private boolean eventoEncerramentoGerado;
}

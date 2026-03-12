package com.cooperative.voting.domain.model;

import com.cooperative.voting.domain.model.enums.StatusSessao;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sessao {

    private UUID id;
    private UUID pautaId;
    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFechamento;
    private StatusSessao status;
    private boolean eventoEncerramentoGerado;

    public static Sessao abrir(UUID pautaId, Integer duracaoMinutos) {

        OffsetDateTime agora = OffsetDateTime.now();

        int duracao = Optional.ofNullable(duracaoMinutos).orElse(10);

        return new Sessao(
                UUID.randomUUID(),
                pautaId,
                agora,
                agora.plusMinutes(duracao),
                StatusSessao.ABERTA,
                false
        );
    }

    public void marcarEncerramentoGerado() {
        this.eventoEncerramentoGerado = true;
    }

    public boolean estaEncerrada() {
        return OffsetDateTime.now().isAfter(this.dataFechamento);
    }

    public StatusSessao statusAtual() {

        return OffsetDateTime.now().isAfter(dataFechamento)
                ? StatusSessao.ENCERRADA
                : StatusSessao.ABERTA;
    }
}
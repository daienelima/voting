package com.cooperative.voting.application.service;

import com.cooperative.voting.application.command.RegistrarVotoCommand;
import com.cooperative.voting.domain.exception.SessaoEncerradaException;
import com.cooperative.voting.domain.exception.VotoDuplicadoException;
import com.cooperative.voting.domain.model.Sessao;
import com.cooperative.voting.domain.model.Voto;
import com.cooperative.voting.domain.port.in.RegistrarVotoUseCase;
import com.cooperative.voting.domain.port.out.CpfValidationPort;
import com.cooperative.voting.domain.port.out.SessaoRepositoryPort;
import com.cooperative.voting.domain.port.out.VotoRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrarVotoService implements RegistrarVotoUseCase {

    private final SessaoRepositoryPort sessaoRepository;
    private final VotoRepositoryPort votoRepository;
    private final CpfValidationPort cpfValidation;

    public RegistrarVotoService(
            SessaoRepositoryPort sessaoRepository,
            VotoRepositoryPort votoRepository,
            CpfValidationPort cpfValidation) {
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
        this.cpfValidation = cpfValidation;
    }

    @Override
    @Transactional
    public void execute(UUID pautaId, RegistrarVotoCommand command) {

        cpfValidation.validar(command.cpf());

        Sessao sessao = sessaoRepository.findSessaoAtivaPorPauta(pautaId)
                .orElseThrow(SessaoEncerradaException::new);

        if (sessao.estaEncerrada()) {
            throw new SessaoEncerradaException();
        }

        if (votoRepository.existsBySessaoIdAndAssociadoId(sessao.getId(), command.associadoId())) {
            throw new VotoDuplicadoException();
        }

        Voto voto = Voto.novo(
                sessao.getId(),
                command.associadoId(),
                command.voto()
        );

        votoRepository.save(voto);
    }
}

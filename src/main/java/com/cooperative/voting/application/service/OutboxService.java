package com.cooperative.voting.application.service;

import com.cooperative.voting.domain.port.out.OutboxEventPort;
import org.springframework.stereotype.Service;

@Service
public class OutboxService {

    private final OutboxEventPort outboxEventPort;

    public OutboxService(OutboxEventPort outboxEventPort) {
        this.outboxEventPort = outboxEventPort;
    }

    public void salvarEvento(Object event) {
        outboxEventPort.salvarEvento(event);
    }
}
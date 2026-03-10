package com.cooperative.voting.domain.port.out;

public interface OutboxEventPort {

    void salvarEvento(Object event);

}
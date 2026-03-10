CREATE TABLE voto (
                      id UUID PRIMARY KEY,
                      sessao_id UUID NOT NULL,
                      associado_id VARCHAR(100) NOT NULL,
                      escolha VARCHAR(10) NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      CONSTRAINT uk_voto UNIQUE (sessao_id, associado_id)
);

CREATE INDEX idx_voto_sessao ON voto(sessao_id);

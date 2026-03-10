CREATE TABLE pauta (
                       id UUID PRIMARY KEY,
                       titulo VARCHAR(255) NOT NULL,
                       descricao TEXT,
                       data_criacao TIMESTAMP NOT NULL
);

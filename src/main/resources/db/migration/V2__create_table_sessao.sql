CREATE TABLE sessao (
                        id UUID PRIMARY KEY,
                        pauta_id UUID NOT NULL,
                        data_abertura TIMESTAMP NOT NULL,
                        data_fechamento TIMESTAMP NOT NULL,

                        CONSTRAINT fk_sessao_pauta
                            FOREIGN KEY (pauta_id)
                                REFERENCES pauta(id)
                                ON DELETE CASCADE,

                        CONSTRAINT ck_datas_validas
                            CHECK (data_fechamento > data_abertura)
);

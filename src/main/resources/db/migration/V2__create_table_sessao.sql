CREATE TABLE sessao (
                        id UUID PRIMARY KEY,
                        pauta_id UUID NOT NULL,
                        data_abertura TIMESTAMP WITH TIME ZONE NOT NULL,
                        data_fechamento TIMESTAMP WITH TIME ZONE NOT NULL,
                        envento_encrramento_gerado boolean NOT NULL DEFAULT false,
                        CONSTRAINT fk_sessao_pauta
                            FOREIGN KEY (pauta_id)
                                REFERENCES pauta(id)
                                ON DELETE CASCADE,

                        CONSTRAINT ck_datas_validas
                            CHECK (data_fechamento > data_abertura)
);

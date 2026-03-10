# Cooperative Voting Service

Sistema de votação desenvolvido para gerenciar pautas, sessões de
votação e registro de votos de associados. A aplicação permite criar
pautas, abrir sessões de votação com duração configurável, registrar
votos e calcular automaticamente o resultado após o encerramento da
sessão.

O projeto foi desenvolvido utilizando Java e Spring Boot, seguindo
princípios de Clean Architecture e Hexagonal Architecture para garantir
baixo acoplamento, testabilidade e facilidade de evolução.

------------------------------------------------------------------------

# Visão Geral

O sistema implementa os seguintes fluxos principais:

-   Cadastro de pautas
-   Abertura de sessões de votação
-   Registro de votos de associados
-   Apuração automática do resultado
-   Publicação de eventos após encerramento da sessão

Cada associado pode votar **apenas uma vez por sessão**.

------------------------------------------------------------------------

# Tecnologias Utilizadas

-   Java 21
-   Spring Boot
-   Spring Data JPA
-   PostgreSQL
-   Flyway
-   MapStruct
-   Lombok
-   Apache Kafka
-   Maven

------------------------------------------------------------------------

# Arquitetura

O projeto segue **Clean Architecture + Hexagonal Architecture (Ports and
Adapters)**.

Essa abordagem separa regras de negócio de frameworks e detalhes de
infraestrutura.

## Camadas

### Domain

Contém as entidades e regras de negócio.

Principais classes:

-   Sessao
-   Voto
-   ResultadoVotacao

### Application

Orquestra os casos de uso da aplicação.

Principais serviços:

-   CriarSessaoService
-   RegistrarVotoService
-   BuscarPautaService
-   EncerrarSessaoService

### Ports

Interfaces que definem contratos entre aplicação e infraestrutura.

-   SessaoRepositoryPort
-   VotoRepositoryPort
-   OutboxEventPort

### Infrastructure

Implementação concreta dos adapters.

Inclui:

-   Controllers REST
-   Repositórios JPA
-   Publicação de eventos Kafka
-   Schedulers

------------------------------------------------------------------------

# Estrutura de Pacotes

    com.cooperative.voting
    │
    ├── domain
    │     ├── model
    │     └── exception
    │
    ├── application
    │     ├── service
    │     ├── port
    │     │      ├── in
    │     │      └── out
    │     └── event
    │
    ├── infrastructure
    │     ├── adapter
    │     │      ├── in
    │     │      │      └── rest
    │     │      └── out
    │     │             ├── persistence
    │     │             └── messaging
    │     │
    │     └── scheduler
    │
    └── config

------------------------------------------------------------------------

# Modelo de Domínio

## Pauta

Campos:

-   id
-   titulo
-   descricao
-   dataCriacao

## Sessao

Campos:

-   id
-   pautaId
-   dataAbertura
-   dataFechamento

A sessão é considerada encerrada quando o horário atual ultrapassa o
horário de fechamento.

## Voto

Campos:

-   id
-   sessaoId
-   associadoId
-   escolha
-   createdAt

Tipos de voto:

-   SIM
-   NAO

------------------------------------------------------------------------

# Regra de Voto Único

Um associado pode votar apenas uma vez por sessão.

Essa regra é garantida por:

1.  Validação no serviço de aplicação
2.  Constraint no banco de dados

------------------------------------------------------------------------

# Cálculo de Resultado

    APROVADO  -> totalSim > totalNao
    REPROVADO -> totalNao > totalSim
    EMPATE    -> totalSim == totalNao

------------------------------------------------------------------------

# Encerramento de Sessão

Um scheduler identifica sessões expiradas e calcula automaticamente o
resultado.

Fluxo:

1.  Buscar sessões expiradas
2.  Contar votos
3.  Gerar evento de sessão encerrada

------------------------------------------------------------------------

# Evento de Sessão Encerrada

``` java
public record SessaoEncerradaEvent(
    UUID sessaoId,
    UUID pautaId,
    long totalSim,
    long totalNao,
    String resultado
) {}
```

------------------------------------------------------------------------

# Outbox Pattern

Para garantir consistência entre persistência e publicação de eventos, o
sistema utiliza o padrão **Outbox**.

Fluxo:

1.  Evento salvo na tabela outbox_event
2.  Worker lê eventos não processados
3.  Evento publicado no Kafka
4.  Evento marcado como processado

------------------------------------------------------------------------

# Estrutura do Banco de Dados

Tabela pauta

-   id
-   titulo
-   descricao
-   data_criacao

Tabela sessao

-   id
-   pauta_id
-   data_abertura
-   data_fechamento

Tabela voto

-   id
-   sessao_id
-   associado_id
-   escolha
-   created_at

Tabela outbox_event

-   id
-   event_type
-   payload
-   created_at
-   processed

------------------------------------------------------------------------

# Executando o Projeto

Pré‑requisitos:

-   Java 21
-   PostgreSQL
-   Maven

Build:

mvn clean install

Executar:

mvn spring-boot:run

------------------------------------------------------------------------

# Documentação da API

Após iniciar a aplicação:

http://localhost:8080/swagger-ui.html

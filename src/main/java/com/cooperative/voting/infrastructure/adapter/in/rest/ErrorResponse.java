package com.cooperative.voting.infrastructure.adapter.in.rest;

import java.time.OffsetDateTime;

public record ErrorResponse(
        OffsetDateTime timestamp,
        int status,
        String message
) {}

package com.cooperative.voting.infrastructure.adapter.out.external;

import com.cooperative.voting.domain.exception.CpfInvalidoException;
import com.cooperative.voting.domain.exception.CpfValidationException;
import com.cooperative.voting.domain.port.out.CpfValidationPort;
import com.cooperative.voting.infrastructure.config.CpfApiProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class CpfValidationHttpAdapter implements CpfValidationPort {

    private final HttpClient httpClient;
    private final CpfApiProperties properties;


    public CpfValidationHttpAdapter(CpfApiProperties properties) {
        this.properties = properties;

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(properties.getTimeoutSeconds()))
                .build();
    }

    @Override
    public void validar(String cpf) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(properties.getBaseUrl() + "/" + cpf))
                .GET()
                .timeout(Duration.ofSeconds(3))
                .build();

        try {

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new CpfInvalidoException();
            }

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CpfValidationException("Erro ao validar CPF externamente", e);
        }
    }
}

package com.cooperative.voting.infrastructure.adapter.out.external;

import com.cooperative.voting.domain.exception.CpfInvalidoException;
import com.cooperative.voting.domain.exception.CpfValidationException;
import com.cooperative.voting.domain.port.out.CpfValidationPort;
import com.cooperative.voting.infrastructure.config.CpfApiProperties;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.Duration;

@Component
public class CpfValidationHttpAdapter implements CpfValidationPort {

    private final HttpClient httpClient;
    private final CpfApiProperties properties;


    public CpfValidationHttpAdapter(CpfApiProperties properties) throws NoSuchAlgorithmException, KeyManagementException {
        this.properties = properties;

        // Create a trust manager that does not validate certificate chains
//        TrustManager[] trustAllCerts = new TrustManager[] {
//            new X509TrustManager() {
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//                public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                }
//                public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                }
//            }
//        };
//
//        // Install the all-trusting trust manager
//        SSLContext sslContext = SSLContext.getInstance("SSL");
//        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(properties.getTimeoutSeconds()))
              //  .sslContext(sslContext)
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

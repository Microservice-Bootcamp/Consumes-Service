package com.rs.consumes.webclient;

import com.rs.consumes.webclient.dto.BankAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class PersonalAccountWebClient {

    @Value("${base.url.personalaccount}")
    private String baseUrl;

    @Autowired
    private WebClient.Builder webClient;

    public Mono<BankAccountDto> findByAccountNumber(String accountNumber) {
        return webClient.baseUrl(baseUrl).build().get()
                .uri("/account/detail/".concat(accountNumber))
                .retrieve()
                .bodyToMono(BankAccountDto.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        return Mono.error(new Exception("Error consumes personal account microservice"));
                    }
                    return Mono.error(error);
                });
    }




}

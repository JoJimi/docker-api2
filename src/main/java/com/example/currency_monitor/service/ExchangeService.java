package com.example.currency_monitor.service;

import com.example.currency_monitor.model.exchange.ExchangeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
public class ExchangeService {
    private static final RestClient restClient = RestClient.create();

    private final String apiUri;
    private final String authKey;

    public ExchangeService(
            @Value("${kexim.api-uri}") String apiUri, @Value("${kexim.auth-key}") String authKey){
        this.apiUri = apiUri;
        this.authKey = authKey;
    }
    public ExchangeResponse getExchangeByCurrency(String currency){
        var exchangesResponse =
                restClient
                .get()
                .uri(apiUri + authKey)
                .retrieve()
                .body(ExchangeResponse[].class);

        if(exchangesResponse == null){      // null인 경우
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // USD
        return Arrays.stream(exchangesResponse)
                .filter(exchangeResponse -> exchangeResponse.cur_unit().equals(currency.toUpperCase()))
                .findFirst()
                .orElse(new ExchangeResponse("USD", "미국 달러", "2000"));
    }
}

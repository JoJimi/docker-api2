package com.example.currency_monitor.model.currency;

import com.example.currency_monitor.model.exchange.ExchangeResponse;

public record CurrencyResponse(String unit, String name, Double rate) {
    public static CurrencyResponse from (ExchangeResponse exchangeResponse){
        return new CurrencyResponse(
                exchangeResponse.cur_unit(),
                exchangeResponse.cur_nm(),
                Double.parseDouble(exchangeResponse.deal_bas_r().replace(",", ""))
        );
    }
}

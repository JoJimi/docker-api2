package com.example.currency_monitor.model.exchange;

public record ExchangeResponse(
        String cur_unit,
        String cur_nm,
        String deal_bas_r
) { }

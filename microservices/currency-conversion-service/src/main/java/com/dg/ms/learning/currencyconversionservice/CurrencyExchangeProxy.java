package com.dg.ms.learning.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange", url = "localhost:8001")
public interface CurrencyExchangeProxy {

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveCurrencyExchangeInfo(
            @PathVariable String from,
            @PathVariable String to
    );


}

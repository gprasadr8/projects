package com.dg.ms.learning.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        Optional<CurrencyExchange> currencyExchangeOptional = currencyExchangeRepository.findByFromAndTo(from, to);
        CurrencyExchange currencyExchange = currencyExchangeOptional.
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Unable to find Data for from:%s and to:%s", from, to)));
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}

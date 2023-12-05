package com.dg.ms.learning.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable int quantity){
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        ResponseEntity<CurrencyConversion> conversionResponseEntity =  new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,uriVariables);
        CurrencyConversion currencyConversion =  conversionResponseEntity.getBody();
        currencyConversion.setQuantity(quantity);
        currencyConversion.setEnvironment(currencyConversion.getEnvironment()+" RestTemplate");
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)));
        return currencyConversion;
    }

    @GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable int quantity){
        CurrencyConversion currencyConversion =  currencyExchangeProxy.retrieveCurrencyExchangeInfo(from,to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setEnvironment(currencyConversion.getEnvironment()+" Feign");
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)));
        return currencyConversion;
    }
}

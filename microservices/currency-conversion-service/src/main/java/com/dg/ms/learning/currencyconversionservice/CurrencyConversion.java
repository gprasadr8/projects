package com.dg.ms.learning.currencyconversionservice;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyConversion {

    private long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private int quantity;
    private BigDecimal totalCalculatedAmount;
    private String environment;

}

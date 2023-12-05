package com.dg.ms.learning.currencyexchangeservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "currency_exchange")
public class CurrencyExchange {
    @Id
    private long id;
    @Column(name="from_currency")
    private String from;
    @Column(name="to_currency")
    private String to;
    private BigDecimal conversionMultiple;
    private String environment;

}

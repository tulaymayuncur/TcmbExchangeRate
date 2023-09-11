package com.tcmb.currency.entities.concretes;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currencies")

public class Currency{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
    private String name;
    @Column(name = "forex_buying")
    private Double forexBuying;
    @Column(name = "forex_selling")
    private Double forexSelling;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "exchange_rate_date")
    private LocalDate exchangeRateDate;
}


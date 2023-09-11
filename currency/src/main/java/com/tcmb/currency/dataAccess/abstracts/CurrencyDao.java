package com.tcmb.currency.dataAccess.abstracts;

import com.tcmb.currency.entities.concretes.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyDao extends JpaRepository<Currency, Integer> {
    List<Currency> findByExchangeRateDate(LocalDate exchangeRateDate);
}


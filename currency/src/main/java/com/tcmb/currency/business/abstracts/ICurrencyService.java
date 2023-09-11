package com.tcmb.currency.business.abstracts;

import com.tcmb.currency.core.utilities.results.DataResult;
import com.tcmb.currency.entities.concretes.Currency;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICurrencyService {
    CompletableFuture<DataResult<List<Currency>>> getAll(String url, LocalDate date);
}


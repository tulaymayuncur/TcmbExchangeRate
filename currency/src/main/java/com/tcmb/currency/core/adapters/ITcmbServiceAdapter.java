package com.tcmb.currency.core.adapters;

import com.tcmb.currency.core.utilities.results.DataResult;
import com.tcmb.currency.entities.concretes.Currency;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ITcmbServiceAdapter {

    CompletableFuture<DataResult<List<Currency>>> getExchangeRate(String url, LocalDate date);
}

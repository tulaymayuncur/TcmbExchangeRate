package com.tcmb.currency.api.controllers;

import com.tcmb.currency.business.abstracts.ICurrencyService;
import com.tcmb.currency.core.utilities.results.DataResult;
import com.tcmb.currency.entities.concretes.Currency;
import com.tcmb.currency.entities.dtos.CurrencyRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/currencies")
public class CurrencyControllers {
    @Autowired
    private final ICurrencyService currencyService;
    public CurrencyControllers(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/get")
    public ResponseEntity<?> get(@RequestBody CurrencyRequestDto currencyRequestDto) throws ExecutionException, InterruptedException {
        CompletableFuture<DataResult<List<Currency>>> result = this.currencyService.getAll(currencyRequestDto.url, currencyRequestDto.date);
        if (result.get().isSuccess()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.badRequest().body(result.get().getMessage());
    }
}

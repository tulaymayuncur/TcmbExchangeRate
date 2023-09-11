package com.tcmb.currency.business.concretes;

import com.tcmb.currency.business.abstracts.ICurrencyService;
import com.tcmb.currency.core.adapters.ITcmbServiceAdapter;
import com.tcmb.currency.core.utilities.results.DataResult;
import com.tcmb.currency.core.utilities.results.ErrorDataResult;
import com.tcmb.currency.core.utilities.results.SuccessDataResult;
import com.tcmb.currency.dataAccess.abstracts.CurrencyDao;
import com.tcmb.currency.entities.concretes.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CurrencyService implements ICurrencyService {
    @Autowired
    private final CurrencyDao currencyDao;
    private final ITcmbServiceAdapter iTcmbServiceAdapter;

    public CurrencyService(CurrencyDao currencyDao, ITcmbServiceAdapter iTcmbServiceAdapter) {
        this.currencyDao = currencyDao;
        this.iTcmbServiceAdapter = iTcmbServiceAdapter;
    }

    @Override
    @Async
    public CompletableFuture<DataResult<List<Currency>>> getAll(String url, LocalDate date) {
        List<Currency> tmpCurrencies = new ArrayList<>();
        try {
            if (!isExchangeRateExist(date).isSuccess()) {
                CompletableFuture<DataResult<List<Currency>>> result = iTcmbServiceAdapter.getExchangeRate(url,date);
                if (!result.get().isSuccess()) {
                    return CompletableFuture.completedFuture(new ErrorDataResult<>(null, result.get().getMessage()));
                }
                tmpCurrencies = result.get().getData();
                currencyDao.saveAll(tmpCurrencies);
                return CompletableFuture.completedFuture(new SuccessDataResult<>(tmpCurrencies, result.get().getMessage()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tmpCurrencies = isExchangeRateExist(date).getData();
        return CompletableFuture.completedFuture(new SuccessDataResult<>(tmpCurrencies, "Kur bilgisi listelendi"));
    }
    

    private DataResult<List<Currency>> isExchangeRateExist(LocalDate date) {
        List<Currency> tmpCurrencies = currencyDao.findByExchangeRateDate(date);
        if (!tmpCurrencies.isEmpty()) {
            return new SuccessDataResult<>(tmpCurrencies, "Kur bilgisi listelendi");
        }
        return new ErrorDataResult<>();
    }
}

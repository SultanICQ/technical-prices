package com.inditext.prices.application;

import com.inditext.prices.domain.Price;
import com.inditext.prices.domain.PriceRepository;
import com.inditext.prices.domain.exception.PriceNotFoundException;

import java.text.SimpleDateFormat;

public class SearchPriceByBrandAndProductAtDate {

    private final PriceRepository repository;
    private final SimpleDateFormat dateFormat;

    public SearchPriceByBrandAndProductAtDate(PriceRepository repository, SimpleDateFormat dateFormat) {
        this.repository = repository;
        this.dateFormat = dateFormat;
    }

    public SearchPriceByBrandAndProductAtDateResponse execute(SearchPriceByBrandAndProductAtDateRequest request) {

        Price price = repository
                .searchByBrandProductAtDate(request.brandId(), request.productId(), request.date())
                .orElseThrow(PriceNotFoundException::new);

        return SearchPriceByBrandAndProductAtDateResponse.from(price, dateFormat);
    }
}

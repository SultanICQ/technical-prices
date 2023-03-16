package com.inditext.prices.application;

import com.inditext.prices.domain.PriceRepository;

public class SearchPriceByBrandAndProductAtDate {

    private final PriceRepository repository;

    public SearchPriceByBrandAndProductAtDate(PriceRepository repository) {
        this.repository = repository;
    }

    public SearchPriceByBrandAndProductAtDateResponse execute(SearchPriceByBrandAndProductAtDateRequest request) {
        return null;
    }
}

package com.technical.prices.application;

import com.technical.prices.domain.Price;
import com.technical.prices.domain.PriceRepository;
import com.technical.prices.domain.exception.InvalidDateException;
import com.technical.prices.domain.exception.PriceNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SearchPriceByBrandAndProductAtDate {

    private final PriceRepository repository;
    private final SimpleDateFormat dateFormat;

    public SearchPriceByBrandAndProductAtDate(PriceRepository repository, SimpleDateFormat dateFormat) {
        this.repository = repository;
        this.dateFormat = dateFormat;
    }

    public SearchPriceByBrandAndProductAtDateResponse execute(SearchPriceByBrandAndProductAtDateRequest request) {

        Price price = repository
                .searchByBrandProductAtDate(request.brandId(), request.productId(), toDate(request.date()))
                .orElseThrow(PriceNotFoundException::new);

        return SearchPriceByBrandAndProductAtDateResponse.from(price, dateFormat);
    }

    private Date toDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new InvalidDateException();
        }
    }
}

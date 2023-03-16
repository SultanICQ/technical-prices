package com.technical.prices.application;

import com.technical.prices.domain.Price;

import java.text.SimpleDateFormat;

public record SearchPriceByBrandAndProductAtDateResponse(
        long brandId,
        long productId,
        long id,
        String startDate,
        String endDate,
        double price
        ) {
    public static SearchPriceByBrandAndProductAtDateResponse from(Price price, SimpleDateFormat dateFormat) {
        return new SearchPriceByBrandAndProductAtDateResponse(
                price.getBrandId(),
                price.getProductId(),
                price.getId(),
                dateFormat.format(price.getStart()),
                dateFormat.format(price.getEnd()),
                price.getPrice()
        );
    }
}

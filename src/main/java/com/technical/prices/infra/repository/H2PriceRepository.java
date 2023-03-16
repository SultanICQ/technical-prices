package com.technical.prices.infra.repository;

import com.technical.prices.domain.Price;
import com.technical.prices.domain.PriceRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class H2PriceRepository implements PriceRepository {
    private H2CrudPriceRepository repository;
    private SimpleDateFormat dateFormat;

    public H2PriceRepository(H2CrudPriceRepository repository, SimpleDateFormat dateFormat) {
        this.repository = repository;
        this.dateFormat = dateFormat;
    }

    @Override
    public Optional<Price> searchByBrandProductAtDate(long brandId, long productId, Date date) {
        return repository.findPriceByBrandIdAndProductIdAndDate(brandId, productId, dateFormat.format(date))
                .map(this::mapPrice);

    }

    private Price mapPrice(H2Price price) {
        return new Price(
                price.getId(),
                price.getBrandId(),
                toDate(price.getStart()),
                toDate(price.getEnd()),
                price.getProductId(),
                price.getPriority(),
                price.getPrice(),
                price.getCurr()
        );
    }

    private Date toDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

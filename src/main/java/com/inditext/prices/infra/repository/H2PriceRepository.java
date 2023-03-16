package com.inditext.prices.infra.repository;

import com.inditext.prices.domain.Price;
import com.inditext.prices.domain.PriceRepository;

import java.util.Date;
import java.util.Optional;

public class H2PriceRepository implements PriceRepository {
    @Override
    public Optional<Price> searchByBrandProductAtDate(long brandId, long productId, Date date) {
        return Optional.empty();
    }
}

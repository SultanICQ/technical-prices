package com.inditext.prices.domain;

import java.util.Date;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> searchByBrandProductAtDate(long brandId, long productId, Date date);
}
